package psk.phone.operator.config.error;

/**
 * Klasa odpowiedzialna za obsługę customowych wyjątków w przypadku, gdy użytkownik o danym adresie e-mail istnieje w bazie.
 */
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
