package modules.business.services.logging;

import modules.business.entities.Account;

import java.util.Comparator;

public class AccountComparator implements Comparator<Account> {
    @Override
    public int compare(Account account1, Account account2) {
        String acc1 = account1.getAccountNumber();
        String acc2 = account2.getAccountNumber();
        return acc1.compareTo(acc2);
    }
}
