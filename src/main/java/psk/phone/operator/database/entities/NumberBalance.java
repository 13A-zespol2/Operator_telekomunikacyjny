package psk.phone.operator.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class NumberBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNumberBalance;
    @OneToOne
    private PhoneNumber phoneNumber;
    private Double balanceAccount;
    private Double balanceInternet;
    private int smsBalance;
}
