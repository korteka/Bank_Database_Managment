package modules.persistence.sources;

import modules.business.entities.Deposit;
import modules.business.entities.InvalidAccountNumberException;
import modules.persistence.exceptions.DataAccessDepositException;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataAccessDeposit {
    public List<Deposit> loadAllDeposits() throws DataAccessDepositException {
        List<Deposit> deposits = new ArrayList<>();
        try (FileReader fileReader = new FileReader("resources/deposit.txt")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine = bufferedReader.readLine();
            while (!"---".equals(currentLine)) {
                try {
                    Deposit currentDeposits = readDeposit(bufferedReader);
                    deposits.add(currentDeposits);
                } catch (InvalidAccountNumberException exception){
                    //
                }

                currentLine = bufferedReader.readLine();
            }
        } catch (IOException exception) {
            throw new DataAccessDepositException("Something went wrong, try again later!", LocalDateTime.now());
        }
        return deposits;
    }

    public void saveAllDeposits(List<Deposit> deposits) {
        try (FileWriter fileWriter = new FileWriter("resources/deposit.txt")) {
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Deposit currentDeposits : deposits) {
                writeDeposit(printWriter, currentDeposits);
            }
            printWriter.println("---");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Deposit readDeposit(BufferedReader bufferedReader) throws IOException, InvalidAccountNumberException {
        String depositReferenceId = bufferedReader.readLine();
        String accountNumber = bufferedReader.readLine();
        int amount = Integer.parseInt(bufferedReader.readLine());
        String branchCity = bufferedReader.readLine();
        LocalDate depositDate = LocalDate.parse(bufferedReader.readLine());
        return new Deposit(depositReferenceId, accountNumber, amount, branchCity, depositDate);
    }

    private void writeDeposit(PrintWriter printWriter, Deposit deposit) {
        printWriter.println("===");
        printWriter.println(deposit.getDepositReferenceId());
        printWriter.println(deposit.getAccountNumber());
        printWriter.println(deposit.getAmount());
        printWriter.println(deposit.getBranchCity());
        printWriter.println(deposit.getDepositDate());
    }
}
