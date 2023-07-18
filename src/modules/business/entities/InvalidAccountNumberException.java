package modules.business.entities;

public class InvalidAccountNumberException extends Exception{
    private final String accountNumber;

    InvalidAccountNumberException(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber(){
        return accountNumber;
    }
}
