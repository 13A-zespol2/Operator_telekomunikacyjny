package psk.phone.operator.database.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Klasa odpowiadająca za tabelę ,,PhoneNumber" (przechowuje informacje na temat numerów klientów).
 */
@NoArgsConstructor
@Entity
@Data
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPhoneNumber;
    @Column(unique=true)
    private String number;
    private String pin;


    public PhoneNumber(String number, String pin) {
        this.number = number;
        this.pin = pin;
    }
}
