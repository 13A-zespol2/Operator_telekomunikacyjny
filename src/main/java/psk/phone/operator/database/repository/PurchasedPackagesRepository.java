package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.PurchasedPackages;

import java.util.Date;
import java.util.Optional;
@Repository
public interface PurchasedPackagesRepository extends JpaRepository<PurchasedPackages, Long> {
    //Optional<PurchasedPackages> findPurchasedPackagesByPhoneNumberAndPackageEndDate(PhoneNumber phoneNumber, Date packageEndDate);
}
