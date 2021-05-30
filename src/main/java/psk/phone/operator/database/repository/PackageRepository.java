package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.phone.operator.database.entities.Package;

import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package, Long> {
    Optional<Package> findByNamePackage(String name);
}
