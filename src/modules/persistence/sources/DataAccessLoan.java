package modules.persistence.sources;

import modules.business.entities.InvalidSocialSecurityNumberException;
import modules.business.entities.Loan;
import modules.persistence.exceptions.DataAccessLoanException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataAccessLoan {
    public List<Loan> loadAllLoans() throws DataAccessLoanException {
        List<Loan> loans = new ArrayList<>();
        try (FileReader fileReader = new FileReader("resources/loan.txt")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine = bufferedReader.readLine();
            while (!"---".equals(currentLine)) {
                try {
                    Loan currentLoan = readLoan(bufferedReader);
                    loans.add(currentLoan);
                } catch (InvalidSocialSecurityNumberException exception){

                }

                currentLine = bufferedReader.readLine();
            }

        } catch (IOException exception) {
            throw new DataAccessLoanException("There has been an error, please try again later!", LocalDateTime.now());
        }
        return loans;
    }

    public void saveAllLoans(List<Loan> loans) {
        try (FileWriter fileWriter = new FileWriter("resources/loan.txt")) {
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Loan currentLoan : loans) {
                writeLoan(printWriter, currentLoan);
            }
            printWriter.println("---");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Loan readLoan(BufferedReader bufferedReader) throws IOException, InvalidSocialSecurityNumberException {
        String loanNumber = bufferedReader.readLine();
        String socialSecurityNumber = bufferedReader.readLine();
        double amount = Double.parseDouble(bufferedReader.readLine());
        String branchCity = bufferedReader.readLine();
        boolean delayedPayments = Boolean.parseBoolean(bufferedReader.readLine());
        return new Loan(loanNumber, socialSecurityNumber, amount, branchCity, delayedPayments);
    }

    private void writeLoan(PrintWriter printWriter, Loan loan) {
        printWriter.println("===");
        printWriter.println(loan.getLoanNumber());
        printWriter.println(loan.getSocialSecurityNumber());
        printWriter.println(loan.getAmount());
        printWriter.println(loan.getBranchCity());
        printWriter.println(loan.isDelayedPayments());
    }
}
