package psk.phone.operator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psk.phone.operator.config.error.OperatorException;
import psk.phone.operator.database.entities.Package;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.PurchasedPackages;
import psk.phone.operator.database.repository.PackageRepository;
import psk.phone.operator.database.repository.PurchasedPackagesRepository;

import javax.management.openmbean.OpenDataException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PackageService {

    private final PurchasedPackagesRepository purchasedPackagesRepository;
    private final PackageRepository packageRepository;

    @Autowired
    public PackageService(PurchasedPackagesRepository purchasedPackagesRepository, PackageRepository packageRepository) {
        this.purchasedPackagesRepository = purchasedPackagesRepository;
        this.packageRepository = packageRepository;
    }

    public PurchasedPackages registerNewUserPackage(PhoneNumber phoneNumber) throws OpenDataException {
        Optional<Package> starter = packageRepository.findByNamePackage("STARTER");
        if (starter.isEmpty()) {
            throw new OpenDataException("Not found STARTER package");
        }
        //TODO
      // PurchasedPackages purchasedPackages = new PurchasedPackages(LocalDate.now(), null, starter.get(), phoneNumber);
        return purchasedPackagesRepository.save(null);
    }

}
