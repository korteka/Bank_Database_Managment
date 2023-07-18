package modules.business.services.utility;

import modules.business.entities.Withdrawal;
import modules.business.services.exceptions.WithdrawalServiceException;
import modules.business.services.logging.WithdrawalComparator;
import modules.persistence.exceptions.DataAccessWithdrawalException;
import modules.persistence.sources.DataAccessWithdrawal;

import java.util.Comparator;
import java.util.List;

public class WithdrawalDatabaseService {
    private final DataAccessWithdrawal dataAccessWithdrawal;

    public WithdrawalDatabaseService(DataAccessWithdrawal dataAccessWithdrawal) {
        this.dataAccessWithdrawal = dataAccessWithdrawal;
    }

    public List<Withdrawal> getWithdrawals() throws WithdrawalServiceException {
        try {
            List<Withdrawal> withdrawalsList = dataAccessWithdrawal.loadAllWithdrawals();
            Comparator<Withdrawal> withdrawalComparator = new WithdrawalComparator();
            withdrawalsList.sort(withdrawalComparator);
            return withdrawalsList;
        } catch (DataAccessWithdrawalException exception) {
            throw new WithdrawalServiceException(exception.getMessage());
        }

    }

    public void addNewWithdrawal(Withdrawal newWithdrawal) throws WithdrawalServiceException {
        try {
            List<Withdrawal> withdrawals = dataAccessWithdrawal.loadAllWithdrawals();
            withdrawals.add(newWithdrawal);
            dataAccessWithdrawal.saveAllWithdrawals(withdrawals);
        } catch (DataAccessWithdrawalException exception) {
            throw new WithdrawalServiceException(exception.getMessage());
        }
    }

    public void removeRejectedWithdrawalFromDatabase(String withdrawalReferenceId) throws WithdrawalServiceException {
        try {
            List<Withdrawal> withdrawals = dataAccessWithdrawal.loadAllWithdrawals();
            int index = getWithdrawalIndex(withdrawals, withdrawalReferenceId);
            if (index != -1) {
                withdrawals.remove(index);
                dataAccessWithdrawal.saveAllWithdrawals(withdrawals);
            }
        } catch (DataAccessWithdrawalException exception) {
            throw new WithdrawalServiceException(exception.getMessage());
        }

    }

    private int getWithdrawalIndex(List<Withdrawal> withdrawals, String withdrawalReferenceId) {
        for (int currentIndex = 0; currentIndex < withdrawals.size(); currentIndex++) {
            Withdrawal currentWithdrawal = withdrawals.get(currentIndex);
            if (currentWithdrawal.getWithdrawalReferenceId().equals(withdrawalReferenceId)) {
                return currentIndex;
            }
        }
        return -1;
    }
}
