package modules.persistence.exceptions;

import java.time.LocalDateTime;

public class DataAccessWithdrawalException extends Exception {
    private final LocalDateTime moment;

    public DataAccessWithdrawalException(String message, LocalDateTime moment) {
        super(message);
        this.moment = moment;
    }
}
