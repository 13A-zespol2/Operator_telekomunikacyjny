package psk.phone.operator.database.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



/**
 * Klasa odpowiadająca za tabelę ,,NumberBalance" (stan wykorzystania pakietów użytkownika).
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class NumberBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNumberBalance;
    @OneToOne
    private PhoneNumber phoneNumber;
    private Double balanceAccount;
    private int balanceInternet;
    private int balanceMinutes;
    private int smsBalance;
}
