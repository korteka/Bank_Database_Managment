package modules.persistence.sources;

import modules.business.entities.Customer;
import modules.business.entities.InvalidSocialSecurityNumberException;
import modules.persistence.exceptions.DataAccessCustomerException;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataAccessCustomer {
    public List<Customer> loadAllCustomers() throws DataAccessCustomerException {
        List<Customer> customers = new ArrayList<>();
        try (FileReader fileReader = new FileReader("resources/customer.txt")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine = bufferedReader.readLine();
            while (!"---".equals(currentLine)) {
                try{
                    Customer currentCustomer = readCustomer(bufferedReader);
                    customers.add(currentCustomer);
                } catch (InvalidSocialSecurityNumberException exception){
                    //
                }

                currentLine = bufferedReader.readLine();
            }

        } catch (IOException exception) {
            throw new DataAccessCustomerException("There has been an error, please try again later!", LocalDateTime.now());
        }
        return customers;
    }

    public void saveAllCustomers(List<Customer> customers) {
        try (FileWriter fileWriter = new FileWriter("resources/customer.txt")) {
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Customer currentCustomer : customers) {
                writeCustomer(printWriter, currentCustomer);
            }
            printWriter.println("---");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Customer readCustomer(BufferedReader bufferedReader) throws IOException, InvalidSocialSecurityNumberException {
        String socialSecurityNumber = bufferedReader.readLine();
        String firstName = bufferedReader.readLine();
        String lastName = bufferedReader.readLine();
        String residence = bufferedReader.readLine();
        LocalDate dateOfBirth = LocalDate.parse(bufferedReader.readLine());
        return new Customer(socialSecurityNumber, firstName, lastName, residence, dateOfBirth);
    }

    private void writeCustomer(PrintWriter printWriter, Customer customer) {
        printWriter.println("===");
        printWriter.println(customer.getSocialSecurityNumber());
        printWriter.println(customer.getFirstName());
        printWriter.println(customer.getLastName());
        printWriter.println(customer.getResidence());
        printWriter.println(customer.getDateOfBirth());
    }
}
