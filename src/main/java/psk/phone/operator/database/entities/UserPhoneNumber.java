package psk.phone.operator.database.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Klasa odpowiadająca za tabelę ,,PhoneNumber" (przechowuje informacje dotyczące przypisania numeru do użytkownika).
 */
@NoArgsConstructor
@Entity
@Data
public class UserPhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserPhoneNumber;
    @JoinColumn(name = "idUser")
    @ManyToOne(optional = false)
    private User user;

    @JoinColumn(name = "idPhoneNumber")
    @ManyToOne(optional = false)
    private PhoneNumber phoneNumber;

    public UserPhoneNumber(User user, PhoneNumber phoneNumber) {
        this.user = user;
        this.phoneNumber = phoneNumber;
    }
}
