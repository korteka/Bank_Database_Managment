package modules.business.services.utility;

import modules.business.entities.Customer;
import modules.business.services.exceptions.CustomerServiceException;
import modules.business.services.logging.CustomerComparator;
import modules.persistence.sources.DataAccessCustomer;
import modules.persistence.exceptions.DataAccessCustomerException;

import java.util.Comparator;
import java.util.List;

public class CustomerDatabaseService {
    private final DataAccessCustomer dataAccessCustomer;

    public CustomerDatabaseService(DataAccessCustomer dataAccessCustomer) {
        this.dataAccessCustomer = dataAccessCustomer;
    }

    public List<Customer> getCustomers() throws CustomerServiceException {
        try {
            List<Customer> customersList = dataAccessCustomer.loadAllCustomers();
            Comparator<Customer> customerComparator = new CustomerComparator();
            customersList.sort(customerComparator);
            return customersList;
        } catch (DataAccessCustomerException dacException) {
            throw new CustomerServiceException(dacException.getMessage());
        }
    }

    public void addNewCustomer(Customer newCustomer) throws CustomerServiceException {
        try {
            List<Customer> customer = dataAccessCustomer.loadAllCustomers();
            customer.add(newCustomer);
            dataAccessCustomer.saveAllCustomers(customer);
        } catch (DataAccessCustomerException dacException) {
            throw new CustomerServiceException(dacException.getMessage());
        }

    }

    public void removeCustomerFromDatabase(String socialSecurityNumber) throws CustomerServiceException {
        try {
            List<Customer> customer = dataAccessCustomer.loadAllCustomers();
            int index = getCustomerIndex(customer, socialSecurityNumber);
            if (index != -1) {
                customer.remove(index);
                dataAccessCustomer.saveAllCustomers(customer);
            }
        } catch (DataAccessCustomerException dacException) {
            throw new CustomerServiceException(dacException.getMessage());
        }

    }

    private int getCustomerIndex(List<Customer> customers, String socialSecurityNumber) {
        for (int currentIndex = 0; currentIndex < customers.size(); currentIndex++) {
            Customer currentCustomer = customers.get(currentIndex);
            if (currentCustomer.getSocialSecurityNumber().equals(socialSecurityNumber)) {
                return currentIndex;
            }
        }
        return -1;
    }
}
