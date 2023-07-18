package modules.business.services.logging;

import modules.business.entities.Deposit;

import java.time.LocalDate;
import java.util.Comparator;

public class DepositComparator implements Comparator<Deposit> {
    @Override
    public int compare(Deposit deposit1, Deposit deposit2) {
        LocalDate dep1 = deposit1.getDepositDate();
        LocalDate dep2 = deposit2.getDepositDate();
        int diff = -dep1.compareTo(dep2);
        if (diff == 0) {
            return -deposit1.getDepositReferenceId().compareTo(deposit2.getDepositReferenceId());
        } else {
            return diff;
        }
    }
}
