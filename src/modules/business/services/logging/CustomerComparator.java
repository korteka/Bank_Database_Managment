package modules.business.services.logging;

import modules.business.entities.Customer;

import java.util.Comparator;

public class CustomerComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer customer1, Customer customer2) {
        String ssn1 = customer1.getSocialSecurityNumber();
        String ssn2 = customer2.getSocialSecurityNumber();
        return - ssn1.compareTo(ssn2);
    }
}
