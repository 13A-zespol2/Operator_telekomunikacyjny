package psk.phone.operator.database.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ContractsPhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContractPhoneNumber;
    @ManyToOne
    private Contracts contracts;
    @ManyToOne
    private PhoneNumber phoneNumber;
    private LocalDate datePurchase;

    public ContractsPhoneNumber(Contracts contracts, PhoneNumber phoneNumber, LocalDate datePurchase) {
        this.contracts = contracts;
        this.phoneNumber = phoneNumber;
        this.datePurchase = datePurchase;
    }
}
