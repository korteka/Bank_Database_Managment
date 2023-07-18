package modules.persistence.exceptions;

import java.time.LocalDateTime;

public class DataAccessAccountException extends Exception {
    private final LocalDateTime moment;

    public DataAccessAccountException(String message, LocalDateTime moment) {
        super(message);
        this.moment = moment;
    }
}
