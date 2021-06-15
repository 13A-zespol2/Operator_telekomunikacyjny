package psk.phone.operator.config.error;

/**
 * Klasa odpowiedzialna za obsługę customowych wyjątków klasy User.
 */
public class NoSuchUserException extends Exception {
    public NoSuchUserException(String message) {
        super(message);
    }
}
