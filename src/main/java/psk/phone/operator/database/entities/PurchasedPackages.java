package psk.phone.operator.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PurchasedPackages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPurchase;
    private LocalDate dateOfPurchase;
    private LocalDate packageEndDate;
    @ManyToOne
    private Package purchasedPackage;
    @ManyToOne
    private PhoneNumber phoneNumber;

    public PurchasedPackages(LocalDate dateOfPurchase, LocalDate packageEndDate, Package purchasedPackage, PhoneNumber phoneNumber) {
        this.dateOfPurchase = dateOfPurchase;
        this.packageEndDate = packageEndDate;
        this.purchasedPackage = purchasedPackage;
        this.phoneNumber = phoneNumber;
    }
}
