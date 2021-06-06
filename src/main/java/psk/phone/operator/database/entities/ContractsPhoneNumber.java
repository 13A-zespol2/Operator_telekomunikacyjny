package psk.phone.operator.database.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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
    private Contract contract;
    @ManyToOne
    private PhoneNumber phoneNumber;
    private LocalDate datePurchase;

    public ContractsPhoneNumber(Contract contract, PhoneNumber phoneNumber, LocalDate datePurchase) {
        this.contract = contract;
        this.phoneNumber = phoneNumber;
        this.datePurchase = datePurchase;
    }
}
