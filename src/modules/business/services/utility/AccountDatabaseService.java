package modules.business.services.utility;

import modules.business.entities.Account;
import modules.business.services.exceptions.AccountServiceException;
import modules.business.services.logging.AccountComparator;
import modules.persistence.sources.DataAccessAccount;
import modules.persistence.exceptions.DataAccessAccountException;

import java.util.Comparator;
import java.util.List;

public class AccountDatabaseService {
    private final DataAccessAccount dataAccessAccount;

    public AccountDatabaseService(DataAccessAccount dataAccessAccount) {
        this.dataAccessAccount = dataAccessAccount;
    }

    public List<Account> getAccounts() throws AccountServiceException {
        try {
            List<Account> accountsList = dataAccessAccount.loadAllAccounts();
            Comparator<Account> accountsComparator = new AccountComparator();
            accountsList.sort((accountsComparator));
            return accountsList;
        } catch (DataAccessAccountException daaException) {
            throw new AccountServiceException(daaException.getMessage());
        }

    }

    public void addNewAccount(Account newAccount) throws AccountServiceException {
        try {
            List<Account> account = dataAccessAccount.loadAllAccounts();
            account.add(newAccount);
            dataAccessAccount.saveAllAccounts(account);
        } catch (DataAccessAccountException daaException) {
            throw new AccountServiceException(daaException.getMessage());
        }

    }

    public void removeAccountFromDatabase(String accountNumber) throws AccountServiceException {
        try {
            List<Account> account = dataAccessAccount.loadAllAccounts();
            int index = getAccountIndex(account, accountNumber);
            if (index != -1) {
                account.remove(index);
                dataAccessAccount.saveAllAccounts(account);
            }
        } catch (DataAccessAccountException daaException) {
            throw new AccountServiceException(daaException.getMessage());
        }

    }

    private int getAccountIndex(List<Account> account, String accountNumber) {
        for (int currentIndex = 0; currentIndex < account.size(); currentIndex++) {
            Account currentAccount = account.get(currentIndex);
            if (currentAccount.getAccountNumber().equals(accountNumber)) {
                return currentIndex;
            }
        }
        return -1;
    }
}
