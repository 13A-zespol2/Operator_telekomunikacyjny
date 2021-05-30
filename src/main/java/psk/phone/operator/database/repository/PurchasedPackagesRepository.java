package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.PurchasedPackages;

import java.util.Date;
import java.util.Optional;

public interface PurchasedPackagesRepository extends JpaRepository<PurchasedPackages, Long> {
    Optional<PurchasedPackages> findPurchasedPackagesByPhoneNumberAndPackageEndDate(PhoneNumber phoneNumber, Date packageEndDate);
}
