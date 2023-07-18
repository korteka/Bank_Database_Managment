package modules.business.entities;

public class InvalidSocialSecurityNumberException extends Exception {

    private final String socialSecurityNumber;

    public InvalidSocialSecurityNumberException(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }
}
