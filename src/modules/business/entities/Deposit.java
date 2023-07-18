package modules.business.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Deposit {
    private String depositReferenceId;
    private String accountNumber;
    private int amount;
    private String branchCity;
    private LocalDate depositDate;

    public Deposit(String depositReferenceId, String accountNumber, int amount, String branchCity, LocalDate depositDate) throws InvalidAccountNumberException {
        validateAccountNumber(accountNumber);
        this.depositReferenceId = depositReferenceId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.branchCity = branchCity;
        this.depositDate = depositDate;
    }

    public String getDepositReferenceId() {
        return depositReferenceId;
    }

    public void setDepositReferenceId(String depositReferenceId) {
        this.depositReferenceId = depositReferenceId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBranchCity() {
        return branchCity;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }

    public LocalDate getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(LocalDate depositDate) {
        this.depositDate = depositDate;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "depositReferenceId='" + depositReferenceId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", branchCity='" + branchCity + '\'' +
                ", depositDate=" + depositDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit deposit = (Deposit) o;
        return amount == deposit.amount && Objects.equals(depositReferenceId, deposit.depositReferenceId) && Objects.equals(accountNumber, deposit.accountNumber) && Objects.equals(branchCity, deposit.branchCity) && Objects.equals(depositDate, deposit.depositDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depositReferenceId, accountNumber, amount, branchCity, depositDate);
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
