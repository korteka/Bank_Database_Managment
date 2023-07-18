package modules.business.services.utility;

import modules.business.entities.Loan;
import modules.business.services.exceptions.LoanServiceException;
import modules.business.services.logging.LoanComparator;
import modules.persistence.sources.DataAccessLoan;
import modules.persistence.exceptions.DataAccessLoanException;

import java.util.Comparator;
import java.util.List;

public class LoanDatabaseService {
    private final DataAccessLoan dataAccessLoan;

    public LoanDatabaseService(DataAccessLoan dataAccessLoan) {
        this.dataAccessLoan = dataAccessLoan;
    }

    public List<Loan> getLoans() throws LoanServiceException {
        try {
            List<Loan> loansList = dataAccessLoan.loadAllLoans();
            Comparator<Loan> loanComparator = new LoanComparator();
            loansList.sort(loanComparator);
            return loansList;
        } catch (DataAccessLoanException dalException) {
            throw new LoanServiceException(dalException.getMessage());
        }

    }

    public void addNewLoan(Loan newLoan) throws LoanServiceException {
        try {
            List<Loan> loan = dataAccessLoan.loadAllLoans();
            loan.add(newLoan);
            dataAccessLoan.saveAllLoans(loan);
        } catch (DataAccessLoanException dalException) {
            throw new LoanServiceException(dalException.getMessage());
        }

    }

    public void removeLoanFromDatabase(String loanNumber) throws LoanServiceException {
        try {
            List<Loan> loan = dataAccessLoan.loadAllLoans();
            int index = getLoanIndex(loan, loanNumber);
            if (index != -1) {
                loan.remove(index);
                dataAccessLoan.saveAllLoans(loan);
            }
        } catch (DataAccessLoanException dalException) {
            throw new LoanServiceException(dalException.getMessage());
        }

    }

    private int getLoanIndex(List<Loan> loans, String loanNumber) throws LoanServiceException {
        for (int currentIndex = 0; currentIndex < loans.size(); currentIndex++) {
            Loan currentLoan = loans.get(currentIndex);
            if (currentLoan.getLoanNumber().equals(loanNumber)) {
                return currentIndex;
            }
        }
        return -1;
    }
}
