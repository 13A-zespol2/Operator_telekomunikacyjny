package psk.phone.operator.database.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Getter
@Setter
public class PurchasedPackages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPurchasePackage;
    @ManyToOne
    private Package aPackage;
    @ManyToOne
    private PhoneNumber phoneNumber;
    private LocalDate datePurchaseDate;

}
