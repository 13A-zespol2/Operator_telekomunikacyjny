package psk.phone.operator.service;

import com.sun.xml.bind.v2.runtime.reflect.Lister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import psk.phone.operator.database.entities.NumberBalance;
import psk.phone.operator.database.entities.Package;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.enumeration.PackageType;
import psk.phone.operator.database.repository.NumberBalanceRepository;
import psk.phone.operator.database.repository.PackageRepository;
import psk.phone.operator.database.repository.PurchasedPackagesRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PackageServiceTest {
    private PurchasedPackagesRepository purchasedPackagesRepository;
    private PackageRepository packageRepository;
    private NumberBalanceRepository numberBalanceRepository;
    private PackageService packageService;

    @BeforeEach
    void setup(){
        numberBalanceRepository = Mockito.mock(NumberBalanceRepository.class);
        packageRepository = Mockito.mock(PackageRepository.class);
        purchasedPackagesRepository = Mockito.mock(PurchasedPackagesRepository.class);
        this.packageService = new PackageService(purchasedPackagesRepository, packageRepository, numberBalanceRepository);
    }

    @Test
    public void ifBuyingPackage(){
        //given
        PhoneNumber phoneNumber = new PhoneNumber("111111111", null);
        NumberBalance numberBalance = new NumberBalance(null, phoneNumber, 0D, 0, 0, 0);
        Mockito.when(numberBalanceRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.of(numberBalance));
        Package packag = new Package("test", 100, 0, 0, 20, "description", PackageType.SMS);

        //when
        packageService.buyPackage(phoneNumber, packag);
        //then
        NumberBalance expectedBalance = new NumberBalance(null, phoneNumber, 0D, 0, 0, 100);
        Mockito.verify(numberBalanceRepository, Mockito.times(1)).save(Mockito.eq(numberBalance));
    }

}