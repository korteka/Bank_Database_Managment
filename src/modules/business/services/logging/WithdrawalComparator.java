package modules.business.services.logging;

import modules.business.entities.Withdrawal;

import java.time.LocalDate;
import java.util.Comparator;

public class WithdrawalComparator implements Comparator<Withdrawal> {
    @Override
    public int compare(Withdrawal withdrawal1, Withdrawal withdrawal2) {
        LocalDate wnr1 = withdrawal1.getWithdrawalDate();
        LocalDate wnr2 = withdrawal2.getWithdrawalDate();
        int diff = -wnr1.compareTo(wnr2);
        if (diff == 0) {
            return -withdrawal1.getWithdrawalReferenceId().compareTo(withdrawal2.getWithdrawalReferenceId());
        } else {
            return diff;
        }
    }
}
