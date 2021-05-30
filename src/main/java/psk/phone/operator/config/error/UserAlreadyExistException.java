package psk.phone.operator.config.error;

import java.util.function.Supplier;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }

}
