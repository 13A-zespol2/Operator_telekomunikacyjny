package psk.phone.operator.database.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import psk.phone.operator.database.entities.*;
import psk.phone.operator.database.enumeration.InvoiceStatusEnum;
import psk.phone.operator.service.InvoiceService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
class UserPhoneNumberRepositoryTest {
    @Autowired
    private UserPhoneNumberRepository userPhoneNumberRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PurchasedPackagesRepository purchasedPackagesRepository;
    @Autowired
    private ContractPhoneNumberRepository contractPhoneNumberRepository;
    @Autowired
    private InvoicesRepository invoicesRepository;
    private double packagePrize;
    private double monthlyCost;


    @Test
    public void creatingInvoice(){


        User byIdUser = userRepository.findByIdUser(1L);
        List<UserPhoneNumber> byUser = userPhoneNumberRepository.findByUser(byIdUser);
        List<PhoneNumber> collect = byUser.stream().map(UserPhoneNumber::getPhoneNumber).collect(Collectors.toList());

        collect.forEach(this::countPrize);

        System.out.println("Package prize = " +  packagePrize);
        System.out.println("Monthly Cost = " + monthlyCost);
        double fullCost = packagePrize + monthlyCost;
        System.out.println("Full Invoice Prize = " + fullCost);

       /* InvoiceService invoiceService = new InvoiceService();
        String s = invoiceService.generateInvoiceNumber();
        System.out.println("Invoice Number = " + s);*/


        Invoices i = invoicesRepository.save(new Invoices(null, null, LocalDate.now(), fullCost, InvoiceStatusEnum.UNPAID, byIdUser));


    }

    private void countPrize(PhoneNumber usPhone) {
        ArrayList<PurchasedPackages> purchasedPackages = purchasedPackagesRepository.findPurchasedPackagesByPhoneNumber(usPhone, LocalDate.now());
        ArrayList<ContractsPhoneNumber> contractsPhoneNumbers = contractPhoneNumberRepository.findMonthlyCost(usPhone, LocalDate.now());

        if (purchasedPackages != null) {
            for (PurchasedPackages purchasedPackage : purchasedPackages) {
                packagePrize += purchasedPackage.getAPackage().getPrice();
            }
            if (contractsPhoneNumbers != null) {
                for (ContractsPhoneNumber contractsPhoneNumber : contractsPhoneNumbers) {
                    monthlyCost += contractsPhoneNumber.getContracts().getMonthlyCost();
                }
            }
        }

    }

}