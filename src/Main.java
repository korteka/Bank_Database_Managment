import modules.business.services.utility.*;
import modules.persistence.sources.*;
import modules.presentation.UserInterface;

public class Main {
    public static void main(String[] args) {
        DataAccessCustomer dataAccessCustomer = new DataAccessCustomer();
        DataAccessAccount dataAccessAccount = new DataAccessAccount();
        DataAccessLoan dataAccessLoan = new DataAccessLoan();
        DataAccessDeposit dataAccessDeposit = new DataAccessDeposit();
        DataAccessWithdrawal dataAccessWithdrawal = new DataAccessWithdrawal();
        CustomerDatabaseService customerDatabaseService = new CustomerDatabaseService(dataAccessCustomer);
        AccountDatabaseService accountDatabaseService = new AccountDatabaseService(dataAccessAccount);
        LoanDatabaseService loanDatabaseService = new LoanDatabaseService(dataAccessLoan);
        DepositDatabaseService depositDatabaseService = new DepositDatabaseService(dataAccessDeposit);
        WithdrawalDatabaseService withdrawalDatabaseService = new WithdrawalDatabaseService(dataAccessWithdrawal);
        UserInterface userInterface = new UserInterface(customerDatabaseService, accountDatabaseService, loanDatabaseService,depositDatabaseService,withdrawalDatabaseService);

        userInterface.showMainMenu();
    }
}