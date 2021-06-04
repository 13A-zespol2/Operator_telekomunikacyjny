package psk.phone.operator.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psk.phone.operator.database.entities.*;
import psk.phone.operator.database.enumeration.InvoiceStatusEnum;
import psk.phone.operator.database.repository.*;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    private final UserPhoneNumberRepository userPhoneNumberRepository;
    private final PurchasedPackagesRepository purchasedPackagesRepository;
    private final ContractPhoneNumberRepository contractPhoneNumberRepository;
    private final InvoicesRepository invoicesRepository;
    private double packagePrize;
    private double monthlyCost;

    @Autowired
    public InvoiceService(UserPhoneNumberRepository userPhoneNumberRepository, PurchasedPackagesRepository purchasedPackagesRepository, ContractPhoneNumberRepository contractPhoneNumberRepository, InvoicesRepository invoicesRepository) {
        this.userPhoneNumberRepository = userPhoneNumberRepository;
        this.purchasedPackagesRepository = purchasedPackagesRepository;
        this.contractPhoneNumberRepository = contractPhoneNumberRepository;
        this.invoicesRepository = invoicesRepository;
    }

    public String generateInvoiceNumber() {

        int invNr = invoicesRepository.allInvoices() + 1;
        String invNrStr = Integer.toString(invNr);

        LocalDate localDate = LocalDate.now();
        DecimalFormat decimalFormat = new DecimalFormat("00");
        return "OP" + "/" + invNrStr + "/" + localDate.getDayOfMonth() + "/" + decimalFormat.format(localDate.getMonth().getValue()) + "/" + localDate.getYear();
    }

    public void creatingInvoice(User user){


        List<UserPhoneNumber> byUser = userPhoneNumberRepository.findByUser(user);
        List<PhoneNumber> collect = byUser.stream().map(UserPhoneNumber::getPhoneNumber).collect(Collectors.toList());



        collect.forEach(this::countPrize);

        System.out.println("Package prize = " +  packagePrize);
        System.out.println("Monthly Cost = " + monthlyCost);
        double fullCost = packagePrize + monthlyCost;
        System.out.println("Full Invoice Prize = " + fullCost);

        String s = generateInvoiceNumber();
        System.out.println("Invoice Number = " + s);


        Invoices i = invoicesRepository.save(new Invoices(null, s, LocalDate.now(), fullCost, InvoiceStatusEnum.UNPAID, user));


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
