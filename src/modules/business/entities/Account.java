package modules.business.entities;

import java.util.Objects;

public class Account {
    private String accountNumber;
    private String customerSocialSecurityNumber;
    private String branchCity;
    private double balance;
    private String currency;

    public Account(String accountNumber, String customerSocialSecurityNumber, String branchCity, double balance, String currency) throws InvalidSocialSecurityNumberException,InvalidAccountNumberException {
        validateSocialSecurityNumber(customerSocialSecurityNumber);
        validateAccountNumber(accountNumber);
        this.accountNumber = accountNumber;
        this.customerSocialSecurityNumber = customerSocialSecurityNumber;
        this.branchCity = branchCity;
        this.balance = balance;
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerSocialSecurityNumber() {
        return customerSocialSecurityNumber;
    }

    public void setCustomerSocialSecurityNumber(String customerSocialSecurityNumber) {
        this.customerSocialSecurityNumber = customerSocialSecurityNumber;
    }

    public String getBranchCity() {
        return branchCity;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", customerSocialSecurityNumber='" + customerSocialSecurityNumber + '\'' +
                ", branchCity='" + branchCity + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 && Objects.equals(accountNumber, account.accountNumber) && Objects.equals(customerSocialSecurityNumber, account.customerSocialSecurityNumber) && Objects.equals(branchCity, account.branchCity) && Objects.equals(currency, account.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, customerSocialSecurityNumber, branchCity, balance, currency);
    }

    private void validateSocialSecurityNumber(String socialSecurityNumber) throws InvalidSocialSecurityNumberException {
        if (!isValidSocialSecurityNumber(socialSecurityNumber)) {
            throw new InvalidSocialSecurityNumberException(socialSecurityNumber);
        }
    }

    private boolean isValidSocialSecurityNumber(String socialSecurityNumber) {
        return socialSecurityNumber.length() == 5;
    }

    private void validateAccountNumber(String accountNumber) throws InvalidAccountNumberException {
        if (!isValidAccountNumber(accountNumber)) {
            throw new InvalidAccountNumberException(accountNumber);
        }
    }

    private boolean isValidAccountNumber(String accountNumber) {
        return accountNumber.length() == 7;
    }
}
