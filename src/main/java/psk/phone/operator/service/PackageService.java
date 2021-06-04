package psk.phone.operator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psk.phone.operator.database.entities.NumberBalance;
import psk.phone.operator.database.entities.Package;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.PurchasedPackages;
import psk.phone.operator.database.repository.NumberBalanceRepository;
import psk.phone.operator.database.repository.PackageRepository;
import psk.phone.operator.database.repository.PurchasedPackagesRepository;

import javax.management.openmbean.OpenDataException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PackageService {

    private final PurchasedPackagesRepository purchasedPackagesRepository;
    private final PackageRepository packageRepository;
    private final NumberBalanceRepository numberBalanceRepository;

    @Autowired
    public PackageService(PurchasedPackagesRepository purchasedPackagesRepository, PackageRepository packageRepository, NumberBalanceRepository numberBalanceRepository) {
        this.purchasedPackagesRepository = purchasedPackagesRepository;
        this.packageRepository = packageRepository;
        this.numberBalanceRepository = numberBalanceRepository;
    }


    public PurchasedPackages buyPackage(PhoneNumber phoneNumber, Package aPackage) {
        PurchasedPackages save = purchasedPackagesRepository.save(
                new PurchasedPackages(null, aPackage, phoneNumber, LocalDate.now()));
        Optional<NumberBalance> byPhoneNumber = numberBalanceRepository.findByPhoneNumber(phoneNumber);
            if(byPhoneNumber.isPresent())
            {
                NumberBalance numberBalance = byPhoneNumber.get();
    /*            NumberBalance.builder().idNumberBalance(numberBalance)*/
            }
return save;
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
