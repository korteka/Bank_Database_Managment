package modules.business.entities;

import java.util.Objects;
public class Loan {
    private String loanNumber;
    private String socialSecurityNumber;
    private double amount;
    private String branchCity;
    private boolean delayedPayments;

    public Loan(String loanNumber, String socialSecurityNumber, double amount, String branchCity, boolean delayedPayments) throws InvalidSocialSecurityNumberException {
        validateSocialSecurityNumber(socialSecurityNumber);
        this.loanNumber = loanNumber;
        this.socialSecurityNumber = socialSecurityNumber;
        this.amount = amount;
        this.branchCity = branchCity;
        this.delayedPayments = delayedPayments;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBranchCity() {
        return branchCity;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }

    public boolean isDelayedPayments() {
        return delayedPayments;
    }

    public void setDelayedPayments(boolean delayedPayments) {
        this.delayedPayments = delayedPayments;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber='" + loanNumber + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", amount=" + amount +
                ", branchCity='" + branchCity + '\'' +
                ", delayedPayments=" + delayedPayments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Double.compare(loan.amount, amount) == 0 && delayedPayments == loan.delayedPayments && Objects.equals(loanNumber, loan.loanNumber) && Objects.equals(socialSecurityNumber, loan.socialSecurityNumber) && Objects.equals(branchCity, loan.branchCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanNumber, socialSecurityNumber, amount, branchCity, delayedPayments);
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


