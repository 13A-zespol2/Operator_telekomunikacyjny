package psk.phone.operator.config.error;

/**
 * Klasa odpowiedzialna za obsługę customowych wyjątków występujących w przypadku niezgodności haseł w procesie rejestracji.
 */
public class UserPasswordException extends RuntimeException {
    public UserPasswordException(String message) {
        super(message);
    }
}
