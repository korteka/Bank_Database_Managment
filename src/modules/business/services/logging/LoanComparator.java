package modules.business.services.logging;

import modules.business.entities.Loan;

import java.util.Comparator;

public class LoanComparator implements Comparator<Loan> {
    @Override
    public int compare(Loan loan1, Loan loan2) {
        String lnr1 = loan1.getLoanNumber();
        String lnr2 = loan2.getLoanNumber();
        return lnr1.compareTo(lnr2);
    }
}
