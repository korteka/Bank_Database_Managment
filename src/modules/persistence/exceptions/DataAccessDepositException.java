package modules.persistence.exceptions;

import java.time.LocalDateTime;

public class DataAccessDepositException extends Exception {
    private final LocalDateTime moment;

    public DataAccessDepositException(String message, LocalDateTime moment) {
        super(message);
        this.moment = moment;
    }
}
