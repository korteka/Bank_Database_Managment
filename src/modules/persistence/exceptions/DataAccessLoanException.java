package modules.persistence.exceptions;

import java.time.LocalDateTime;

public class DataAccessLoanException extends Exception {
    private final LocalDateTime moment;

    public DataAccessLoanException(String message, LocalDateTime moment) {
        super(message);
        this.moment = moment;
    }
}
