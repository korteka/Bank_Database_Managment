package modules.persistence.sources;

import modules.business.entities.Account;
import modules.business.entities.InvalidAccountNumberException;
import modules.business.entities.InvalidSocialSecurityNumberException;
import modules.persistence.exceptions.DataAccessAccountException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataAccessAccount {
    public List<Account> loadAllAccounts() throws DataAccessAccountException {
        List<Account> accounts = new ArrayList<>();
        try (FileReader fileReader = new FileReader("resources/account.txt")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine = bufferedReader.readLine();
            while (!"---".equals(currentLine)) {
                try {
                    Account currentAccount = readAccount(bufferedReader);
                    accounts.add(currentAccount);
                } catch (InvalidSocialSecurityNumberException exception) {
                    //
                } catch (InvalidAccountNumberException exception) {
                    //
                }

                currentLine = bufferedReader.readLine();
            }

        } catch (IOException exception) {
            throw new DataAccessAccountException("There has been an error, please try again later!", LocalDateTime.now());
        }
        return accounts;
    }

    public void saveAllAccounts(List<Account> accounts) {
        try (FileWriter fileWriter = new FileWriter("resources/account.txt")) {
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Account currentAccount : accounts) {
                writeAccount(printWriter, currentAccount);
            }
            printWriter.println("---");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Account readAccount(BufferedReader bufferedReader) throws IOException, InvalidSocialSecurityNumberException, InvalidAccountNumberException {
        String accountNumber = bufferedReader.readLine();
        String customerSocialSecurityNumber = bufferedReader.readLine();
        String branchCity = bufferedReader.readLine();
        double balance = Double.parseDouble(bufferedReader.readLine());
        String currency = bufferedReader.readLine();
        return new Account(accountNumber, customerSocialSecurityNumber, branchCity, balance, currency);
    }

    private void writeAccount(PrintWriter printWriter, Account account) {
        printWriter.println("===");
        printWriter.println(account.getAccountNumber());
        printWriter.println(account.getCustomerSocialSecurityNumber());
        printWriter.println(account.getBranchCity());
        printWriter.println(account.getBalance());
        printWriter.println(account.getCurrency());
    }
}
