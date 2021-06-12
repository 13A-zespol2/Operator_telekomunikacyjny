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
import psk.phone.operator.transport.dto.PackageDto;

import java.time.LocalDate;
import java.util.List;
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

    public NumberBalance buyPackage(PhoneNumber phoneNumber, Package aPackage) {
        NumberBalance balanceUpdated = null;
        PurchasedPackages save = purchasedPackagesRepository.save(
                new PurchasedPackages(null, aPackage, phoneNumber, LocalDate.now()));
        Optional<NumberBalance> byPhoneNumber = numberBalanceRepository.findByPhoneNumber(phoneNumber);
        if (byPhoneNumber.isPresent()) {
            NumberBalance numberBalance = byPhoneNumber.get();
            numberBalance.setBalanceMinutes(numberBalance.getBalanceMinutes() + aPackage.getNumberOfMinutes());
            numberBalance.setBalanceInternet(numberBalance.getBalanceInternet() + aPackage.getNumberOfInternet());
            numberBalance.setSmsBalance(numberBalance.getSmsBalance() + aPackage.getNumberOfSms());

            balanceUpdated = numberBalanceRepository.save(numberBalance);
        }
        return balanceUpdated;
    }

}
