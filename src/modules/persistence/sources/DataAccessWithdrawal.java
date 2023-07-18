package modules.persistence.sources;

import modules.business.entities.InvalidAccountNumberException;
import modules.business.entities.Withdrawal;
import modules.persistence.exceptions.DataAccessWithdrawalException;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataAccessWithdrawal {
    public List<Withdrawal> loadAllWithdrawals() throws DataAccessWithdrawalException {
        List<Withdrawal> withdrawals = new ArrayList<>();
        try (FileReader fileReader = new FileReader("resources/withdrawal.txt")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine = bufferedReader.readLine();
            while (!"---".equals(currentLine)) {
                try {
                    Withdrawal currentWithdrawal = readWithdrawal(bufferedReader);
                    withdrawals.add(currentWithdrawal);
                } catch (InvalidAccountNumberException exception) {
                    //
                }

                currentLine = bufferedReader.readLine();
            }

        } catch (IOException exception) {
            throw new DataAccessWithdrawalException("Something went wrong, try again later!", LocalDateTime.now());
        }
        return withdrawals;
    }

    public void saveAllWithdrawals(List<Withdrawal> withdrawals) {
        try (FileWriter fileWriter = new FileWriter("resources/withdrawal.txt")) {
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Withdrawal currentWithdrawals : withdrawals) {
                writeWithdrawal(printWriter, currentWithdrawals);
            }
            printWriter.println("---");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Withdrawal readWithdrawal(BufferedReader bufferedReader) throws IOException, InvalidAccountNumberException {
        String withdrawalReferenceId = bufferedReader.readLine();
        String accountNumber = bufferedReader.readLine();
        double amount = Double.parseDouble(bufferedReader.readLine());
        boolean hasWithdrawalFee = Boolean.parseBoolean(bufferedReader.readLine());
        LocalDate withdrawalDate = LocalDate.parse(bufferedReader.readLine());
        return new Withdrawal(withdrawalReferenceId, accountNumber, amount, hasWithdrawalFee, withdrawalDate);
    }

    private void writeWithdrawal(PrintWriter printWriter, Withdrawal withdrawal) {
        printWriter.println("===");
        printWriter.println(withdrawal.getWithdrawalReferenceId());
        printWriter.println(withdrawal.getAccountNumber());
        printWriter.println(withdrawal.getAmount());
        printWriter.println(withdrawal.itHasWithdrawalFee());
        printWriter.println(withdrawal.getWithdrawalDate());
    }
}
