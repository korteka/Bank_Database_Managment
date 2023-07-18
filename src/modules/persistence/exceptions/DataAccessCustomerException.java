package modules.persistence.exceptions;

import java.time.LocalDateTime;

public class DataAccessCustomerException extends Exception {
    private final LocalDateTime moment;

    public DataAccessCustomerException(String message, LocalDateTime moment) {
        super(message);
        this.moment = moment;
    }
}
