package modules.presentation;

import modules.business.entities.*;
import modules.business.services.exceptions.*;
import modules.business.services.utility.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

public class UserInterface {
    private final BufferedReader bufferedReader;
    private final CustomerDatabaseService customerDatabaseService;
    private final AccountDatabaseService accountDatabaseService;
    private final LoanDatabaseService loanDatabaseService;
    private final DepositDatabaseService depositDatabaseService;
    private final WithdrawalDatabaseService withdrawalDatabaseService;

    public UserInterface(CustomerDatabaseService customerDatabaseService, AccountDatabaseService accountDatabaseService, LoanDatabaseService loanDatabaseService, DepositDatabaseService depositDatabaseService, WithdrawalDatabaseService withdrawalDatabaseService) {
        /* InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);*/
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        this.customerDatabaseService = customerDatabaseService;
        this.accountDatabaseService = accountDatabaseService;
        this.loanDatabaseService = loanDatabaseService;
        this.depositDatabaseService = depositDatabaseService;
        this.withdrawalDatabaseService = withdrawalDatabaseService;
    }

    private String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void showMainMenu() {
        String option;
        do {
            printOptions();
            System.out.print("Please choose your option:");
            option = readLine();
            System.out.println("Your option is: " + option);
            handleOption(option);
        } while (shouldContinue(option));
    }

    private void printOptions() {
        System.out.println();
        System.out.println("1 - Show entire customer data");
        System.out.println("2 - Add new customer");
        System.out.println("3 - Remove existing customer");
        System.out.println("4 - Show all accounts data");
        System.out.println("5 - Add new account");
        System.out.println("6 - Remove existing account");
        System.out.println("7 - Show all loans data");
        System.out.println("8 - Add new loan");
        System.out.println("9 - Remove existing loan");
        System.out.println("10 - Show all deposits");
        System.out.println("11 - Add new deposit");
        System.out.println("12 - Remove rejected deposit");
        System.out.println("13 - Show all withdrawals");
        System.out.println("14 - Add new withdrawal");
        System.out.println("15 - Remove rejected withdrawal");
        System.out.println("0 - Exit application");
        System.out.println("-----------------------------");
    }

    private void handleOption(String option) {
        switch (option) {
            case "1":
                handlePrintCustomerData();
                break;
            case "2":
                handleAddNewCustomer();
                break;
            case "3":
                handleRemoveExistingCustomer();
                break;
            case "4":
                handlePrintAccountData();
                break;
            case "5":
                handleAddNewAccount();
                break;
            case "6":
                handleRemoveExistingAccount();
                break;
            case "7":
                handlePrintLoanData();
                break;
            case "8":
                handleAddNewLoan();
                break;
            case "9":
                handleRemoveExistingLoan();
                break;
            case "10":
                handlePrintDepositsData();
                break;
            case "11":
                handleAddNewDeposit();
                break;
            case "12":
                handleRemoveRejectedDeposit();
                break;
            case "13":
                handlePrintWithdrawalsData();
                break;
            case "14":
                handleAddNewWithdrawal();
                break;
            case "15":
                handleRemoveRejectedWithdrawal();
                break;
            case "0":
                handleExitApplication();
                break;
            default:
                handleInvalidOption(option);
        }
    }

    private void handleRemoveExistingCustomer() {
        System.out.println("Remove existing customer");
        try {
            System.out.println("Please enter social security number: ");
            String socialSecurityNumber = readLine();
            if (confirm()) {
                customerDatabaseService.removeCustomerFromDatabase(socialSecurityNumber);
            }
        } catch (CustomerServiceException exception) {
            System.out.println(exception.getMessage());
        }

    }

    private void handleAddNewCustomer() {
        System.out.println("Add new customer");
        try {
            Customer newCustomer = readCustomer();
            customerDatabaseService.addNewCustomer(newCustomer);
        } catch (CustomerServiceException exception) {
            System.out.println(exception.getMessage());
        } catch (InvalidSocialSecurityNumberException exception) {
            System.out.println(exception.getMessage() + " is not a valid SSN.");
        }
    }

    private Customer readCustomer() throws InvalidSocialSecurityNumberException {
        System.out.println("Please enter social security number: ");
        String socialSecurityNumber = readLine();
        System.out.println("Please enter first name: ");
        String firstName = readLine();
        System.out.println("Please enter last name: ");
        String lastName = readLine();
        System.out.println("Please enter residence: ");
        String residence = readLine();
        System.out.println("Please enter date of birth: ");
        LocalDate dateOfBirth = LocalDate.parse(readLine());
        return new Customer(socialSecurityNumber, firstName, lastName, residence, dateOfBirth);
    }

    private void handlePrintCustomerData() {
        System.out.println("Print customer data");
        try {
            List<Customer> customers = customerDatabaseService.getCustomers();
            printCustomerData(customers);
        } catch (CustomerServiceException customerServiceException) {
            System.out.println(customerServiceException.getMessage());
        }

    }

    private void printCustomerData(List<Customer> customers) {
        for (Customer currentCustomer : customers) {
            System.out.println(currentCustomer);
        }
    }

    private void handleRemoveExistingAccount() {
        System.out.println("Remove existing account");
        try {
            System.out.println("Please enter account number: ");
            String accountNumber = readLine();
            if (confirm()) {
                accountDatabaseService.removeAccountFromDatabase(accountNumber);
            }
        } catch (AccountServiceException exception) {
            System.out.println(exception.getMessage());
        }

    }

    private void handleAddNewAccount() {
        System.out.println("Add new account");
        try {
            Account newAccount = readAccount();
            accountDatabaseService.addNewAccount(newAccount);
        } catch (AccountServiceException exception) {
            System.out.println(exception.getMessage());
        } catch (InvalidSocialSecurityNumberException exception) {
            System.out.println(exception.getMessage() + " is not a valid SSN.");
        }catch (InvalidAccountNumberException exception) {
            System.out.println(exception.getMessage() + " is not a valid account number.");
        }

    }

    private Account readAccount() throws InvalidSocialSecurityNumberException, InvalidAccountNumberException {
        System.out.println("Please enter account number: ");
        String accountNumber = readLine();
        System.out.println("Please enter customer social security number: ");
        String customerSocialSecurityNumber = readLine();
        System.out.println("Please enter bank branch city: ");
        String branchCity = readLine();
        System.out.println("Please enter balance: ");
        double balance = Double.parseDouble(readLine());
        System.out.println("Please enter currency: ");
        String currency = readLine();
        return new Account(accountNumber, customerSocialSecurityNumber, branchCity, balance, currency);
    }

    private void handlePrintAccountData() {
        System.out.println("Print customer data");
        try {
            List<Account> accounts = accountDatabaseService.getAccounts();
            printAccountData(accounts);
        } catch (AccountServiceException accountServiceException) {
            System.out.println(accountServiceException.getMessage());
        }

    }

    private void printAccountData(List<Account> accounts) {
        for (Account currentAccount : accounts) {
            System.out.println(currentAccount);
        }
    }

    private void handleRemoveExistingLoan() {
        System.out.println("Remove existing loan from database");
        try {
            System.out.println("Please enter loan number: ");
            String loanNumber = readLine();
            if (confirm()) {
                loanDatabaseService.removeLoanFromDatabase(loanNumber);
            }
        } catch (LoanServiceException exception) {
            System.out.println(exception.getMessage());
        }

    }

    private void handleAddNewLoan() {
        System.out.println("Add new loan");
        try {
            Loan newLoan = readLoan();
            loanDatabaseService.addNewLoan(newLoan);
        } catch (LoanServiceException exception) {
            System.out.println(exception.getMessage());
        } catch (InvalidSocialSecurityNumberException exception) {
            System.out.println(exception.getMessage() + " is not a valid SSN.");
        }

    }

    private Loan readLoan() throws InvalidSocialSecurityNumberException {
        System.out.println("Please enter loan number: ");
        String loanNumber = readLine();
        System.out.println("Please enter social security number: ");
        String socialSecurityNumber = readLine();
        System.out.println("Please enter amount: ");
        double amount = Double.parseDouble(readLine());
        System.out.println("Please enter bank branch city: ");
        String residence = readLine();
        System.out.println("Please enter if customer has delayed payments (true/false): ");
        boolean delayedPayments = Boolean.parseBoolean(readLine());
        return new Loan(loanNumber, socialSecurityNumber, amount, residence, delayedPayments);
    }

    private void handlePrintLoanData() {
        System.out.println("Print loan data");
        try {
            List<Loan> loans = loanDatabaseService.getLoans();
            printLoanData(loans);
        } catch (LoanServiceException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void printLoanData(List<Loan> loans) {
        for (Loan currentLoan : loans) {
            System.out.println(currentLoan);
        }
    }

    private void handleRemoveRejectedDeposit() {
        System.out.println("Remove existing deposit from database");
        try {
            System.out.println("Please enter deposit reference id: ");
            String depositReferenceId = readLine();
            if (confirm()) {
                depositDatabaseService.removeRejectedDepositFromDatabase(depositReferenceId);
            }
        } catch (DepositServiceException exception) {
            System.out.println(exception.getMessage());
        }

    }

    private void handleAddNewDeposit() {
        System.out.println("Add new deposit");
        try {
            Deposit newDeposit = readDeposit();
            depositDatabaseService.addNewDeposit(newDeposit);
        } catch (DepositServiceException exception) {
            System.out.println(exception.getMessage());
        } catch (InvalidAccountNumberException exception) {
            System.out.println(exception.getMessage() + " is not a valid account number.");
        }
    }

    private Deposit readDeposit() throws InvalidAccountNumberException {
        System.out.println("Please enter deposit reference id: ");
        String depositReferenceId = readLine();
        System.out.println("Please enter account number: ");
        String accountNumber = readLine();
        System.out.println("Please enter amount: ");
        int amount = Integer.parseInt(readLine());
        System.out.println("Please enter bank branch city: ");
        String branchCity = readLine();
/*        System.out.println("Please enter deposit date: ");*/
        LocalDate depositDate = LocalDate.parse(readLine());
//        LocalDate depositDate = LocalDate.now();
        return new Deposit(depositReferenceId, accountNumber, amount, branchCity, depositDate);
    }

    private void handlePrintDepositsData() {
        System.out.println("Print deposit data");
        try {
            List<Deposit> deposits = depositDatabaseService.getDeposits();
            printDepositData(deposits);
        } catch (DepositServiceException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void printDepositData(List<Deposit> deposits) {
        for (Deposit currentDeposit : deposits) {
            System.out.println(currentDeposit);
        }
    }

    private void handleRemoveRejectedWithdrawal() {
        System.out.println("Remove existing withdrawal");
        try {
            System.out.println("Please enter withdrawal reference id: ");
            String withdrawalReferenceId = readLine();
            if (confirm()) {
                withdrawalDatabaseService.removeRejectedWithdrawalFromDatabase(withdrawalReferenceId);
            }
        } catch (WithdrawalServiceException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void handleAddNewWithdrawal() {
        System.out.println("Add new withdrawal");
        try {
            Withdrawal newWithdrawal = readWithdrawal();
            withdrawalDatabaseService.addNewWithdrawal(newWithdrawal);
        } catch (WithdrawalServiceException exception) {
            System.out.println(exception.getMessage());
        } catch (InvalidAccountNumberException exception) {
            System.out.println(exception.getMessage() + " is not a valid account number.");
        }
    }

    private Withdrawal readWithdrawal() throws InvalidAccountNumberException {
        System.out.println("Please enter withdrawal reference id: ");
        String withdrawalReferenceId = readLine();
        System.out.println("Please enter enter account number: ");
        String accountNumber = readLine();
        System.out.println("Please enter amount: ");
        double amount = Double.parseDouble(readLine());
        System.out.println("Please specify with true or false if the withdrawal has a perceived fee: ");
        boolean hasWithdrawalFee = Boolean.parseBoolean(readLine());
/*        System.out.println("Please enter withdrawal date: ");
        LocalDate withdrawalDate = LocalDate.parse(readLine());*/
        LocalDate withdrawalDate = LocalDate.now();
        return new Withdrawal(withdrawalReferenceId, accountNumber, amount, hasWithdrawalFee, withdrawalDate);
    }

    private void handlePrintWithdrawalsData() {
        try {
            System.out.println("Print all withdrawals");
            List<Withdrawal> withdrawals = withdrawalDatabaseService.getWithdrawals();
            printWithdrawalsData(withdrawals);
        } catch (WithdrawalServiceException exception) {
            System.out.println(exception.getMessage());
        }

    }

    private void printWithdrawalsData(List<Withdrawal> withdrawals) {
        for (Withdrawal currentWithdrawal : withdrawals) {
            System.out.println(currentWithdrawal);
        }
    }

    private boolean confirm() {
        System.out.print("Are you sure? (yes/no) ");
        String answer = readLine();
        if ("yes".equalsIgnoreCase(answer)) {
            return true;
        }
        if ("no".equalsIgnoreCase(answer)) {
            return false;
        }
        System.out.print(answer + " is not a valid answer!");
        return false;
    }

    private void handleExitApplication() {
        System.out.println("Exiting application... Bye!");
    }

    private void handleInvalidOption(String option) {
        System.out.println(option + " is invalid");
    }

    private boolean shouldContinue(String option) {
        return !"0".equals(option);

    }
}
