package psk.phone.operator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psk.phone.operator.database.entities.Package;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.PurchasedPackages;
import psk.phone.operator.database.repository.PurchasedPackagesRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PackageBuyService {
    private final PurchasedPackagesRepository purchasedPackagesRepository;

    @Autowired
    public PackageBuyService(PurchasedPackagesRepository purchasedPackagesRepository) {
        this.purchasedPackagesRepository = purchasedPackagesRepository;
    }

    public Optional<PurchasedPackages> buySinglePackage(PhoneNumber phoneNumber, Package chosenPackage) {
        PurchasedPackages save = purchasedPackagesRepository.save(new PurchasedPackages(null, chosenPackage, phoneNumber, LocalDate.now()));
        return Optional.of(save);
    }

}
