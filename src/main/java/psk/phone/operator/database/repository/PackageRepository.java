package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.Package;
import psk.phone.operator.database.entities.PurchasedPackages;

import java.util.ArrayList;
import java.util.Optional;
@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    Optional<Package> findByNamePackage(String name);

}
