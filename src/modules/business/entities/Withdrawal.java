package modules.business.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Withdrawal {
    private String withdrawalReferenceId;
    private String accountNumber;
    private double amount;
    private boolean hasWithdrawalFee;
    private LocalDate withdrawalDate;

    public Withdrawal(String withdrawalReferenceId, String accountNumber, double amount, boolean hasWithdrawalFee, LocalDate withdrawalDate) throws InvalidAccountNumberException {
        validateAccountNumber(accountNumber);
        this.withdrawalReferenceId = withdrawalReferenceId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.hasWithdrawalFee = hasWithdrawalFee;
        this.withdrawalDate = withdrawalDate;
    }

    public String getWithdrawalReferenceId() {
        return withdrawalReferenceId;
    }

    public void setWithdrawalReferenceId(String withdrawalReferenceId) {
        this.withdrawalReferenceId = withdrawalReferenceId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean itHasWithdrawalFee() {
        return hasWithdrawalFee;
    }

    public void setHasWithdrawalFee(boolean hasWithdrawalFee) {
        this.hasWithdrawalFee = hasWithdrawalFee;
    }

    public LocalDate getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(LocalDate withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    @Override
    public String toString() {
        return "Withdrawal{" +
                "withdrawalReferenceId='" + withdrawalReferenceId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", hasWithdrawalFee=" + hasWithdrawalFee +
                ", withdrawalDate=" + withdrawalDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Withdrawal that = (Withdrawal) o;
        return Double.compare(that.amount, amount) == 0 && hasWithdrawalFee == that.hasWithdrawalFee && Objects.equals(withdrawalReferenceId, that.withdrawalReferenceId) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(withdrawalDate, that.withdrawalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(withdrawalReferenceId, accountNumber, amount, hasWithdrawalFee, withdrawalDate);
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
