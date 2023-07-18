package modules.business.services.utility;

import modules.business.entities.Deposit;
import modules.business.services.exceptions.DepositServiceException;
import modules.business.services.logging.DepositComparator;
import modules.persistence.exceptions.DataAccessDepositException;
import modules.persistence.sources.DataAccessDeposit;

import java.util.Comparator;
import java.util.List;

public class DepositDatabaseService {
    private final DataAccessDeposit dataAccessDeposit;

    public DepositDatabaseService(DataAccessDeposit dataAccessDeposit) {
        this.dataAccessDeposit = dataAccessDeposit;
    }

    public List<Deposit> getDeposits() throws DepositServiceException {
        try {
            List<Deposit> depositsList = dataAccessDeposit.loadAllDeposits();
            Comparator<Deposit> depositComparator = new DepositComparator();
            depositsList.sort(depositComparator);
            return depositsList;
        } catch (DataAccessDepositException exception) {
            throw new DepositServiceException(exception.getMessage());
        }

    }

    public void addNewDeposit(Deposit newDeposit) throws DepositServiceException {
        try {
            List<Deposit> deposits = dataAccessDeposit.loadAllDeposits();
            deposits.add(newDeposit);
            dataAccessDeposit.saveAllDeposits(deposits);
        } catch (DataAccessDepositException exception) {
            throw new DepositServiceException(exception.getMessage());
        }
    }

    public void removeRejectedDepositFromDatabase(String depositReferenceId) throws DepositServiceException {
        try {
            List<Deposit> deposits = dataAccessDeposit.loadAllDeposits();
            int index = getDepositIndex(deposits, depositReferenceId);
            if (index != -1) {
                deposits.remove(index);
                dataAccessDeposit.saveAllDeposits(deposits);
            }
        } catch (DataAccessDepositException exception) {
            throw new DepositServiceException(exception.getMessage());
        }

    }

    private int getDepositIndex(List<Deposit> deposits, String depositReferenceId) {
        for (int currentIndex = 0; currentIndex < deposits.size(); currentIndex++) {
            Deposit currentDeposit = deposits.get(currentIndex);
            if (currentDeposit.getDepositReferenceId().equals(depositReferenceId)) {
                return currentIndex;
            }
        }
        return -1;
    }
}
