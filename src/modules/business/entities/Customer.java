package modules.business.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {
    private String socialSecurityNumber;
    private String firstName;
    private String lastName;
    private String residence;
    private LocalDate dateOfBirth;

    public Customer(String socialSecurityNumber, String firstName, String lastName, String residence, LocalDate dateOfBirth) throws InvalidSocialSecurityNumberException {
        validateSocialSecurityNumber(socialSecurityNumber);
        this.socialSecurityNumber = socialSecurityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.residence = residence;
        this.dateOfBirth = dateOfBirth;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", residence='" + residence + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(socialSecurityNumber, customer.socialSecurityNumber) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(residence, customer.residence) && Objects.equals(dateOfBirth, customer.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socialSecurityNumber, firstName, lastName, residence, dateOfBirth);
    }

    private void validateSocialSecurityNumber(String socialSecurityNumber) throws InvalidSocialSecurityNumberException {
        if (!isValidSocialSecurityNumber(socialSecurityNumber)) {
            throw new InvalidSocialSecurityNumberException(socialSecurityNumber);
        }
    }

    private boolean isValidSocialSecurityNumber(String socialSecurityNumber) {
        return socialSecurityNumber.length() == 5;
    }
}
