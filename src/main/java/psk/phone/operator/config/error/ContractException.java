package psk.phone.operator.config.error;

/**
 * Klasa odpowiedzialna za obsługę customowych wyjątków klasy Contracts.
 */
public class ContractException extends Exception {
    public ContractException(String message) {
        super(message);
    }
}
