package psk.phone.operator.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psk.phone.operator.database.entities.*;
import psk.phone.operator.database.enumeration.InvoiceStatusEnum;
import psk.phone.operator.database.repository.ContractPhoneNumberRepository;
import psk.phone.operator.database.repository.InvoicesRepository;
import psk.phone.operator.database.repository.PurchasedPackagesRepository;
import psk.phone.operator.database.repository.UserPhoneNumberRepository;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    private final UserPhoneNumberRepository userPhoneNumberRepository;
    private final PurchasedPackagesRepository purchasedPackagesRepository;
    private final ContractPhoneNumberRepository contractPhoneNumberRepository;
    private final InvoicesRepository invoicesRepository;


    @Autowired
    public InvoiceService(UserPhoneNumberRepository userPhoneNumberRepository, PurchasedPackagesRepository purchasedPackagesRepository, ContractPhoneNumberRepository contractPhoneNumberRepository, InvoicesRepository invoicesRepository) {
        this.userPhoneNumberRepository = userPhoneNumberRepository;
        this.purchasedPackagesRepository = purchasedPackagesRepository;
        this.contractPhoneNumberRepository = contractPhoneNumberRepository;
        this.invoicesRepository = invoicesRepository;
    }


    private String generateInvoiceNumber() {

        int invNr = invoicesRepository.allInvoices() + 1;
        String invNrStr = Integer.toString(invNr);

        LocalDate localDate = LocalDate.now();
        DecimalFormat decimalFormat = new DecimalFormat("00");
        return "OP" + "/" + invNrStr + "/" + decimalFormat.format(localDate.getDayOfMonth())
                + "/" + decimalFormat.format(localDate.getMonth().getValue()) + "/" + localDate.getYear();
    }

    public void creatingInvoice(User user) {


        List<UserPhoneNumber> byUser = userPhoneNumberRepository.findByUser(user);
        List<PhoneNumber> collect = byUser.stream()
                .map(UserPhoneNumber::getPhoneNumber).collect(Collectors.toList());

        String s = generateInvoiceNumber();


        Invoices i = invoicesRepository.save(new Invoices(null, s, LocalDate.now(), price(collect), InvoiceStatusEnum.UNPAID, user));


    }

    private double price(List<PhoneNumber> usPhone) {
        double fullCostInvoiceUser = 0;
        LocalDate now = LocalDate.now();

        for (PhoneNumber phoneNumber : usPhone) {
            List<PurchasedPackages> purchasedPackages = purchasedPackagesRepository.findPurchasedPackagesByPhoneNumber(phoneNumber, now.minusDays(30), now);
            fullCostInvoiceUser += purchasedPackages != null ? purchasedPackages.stream().mapToDouble(e -> e.getAPackage().getPrice()).sum() : 0;

            List<ContractsPhoneNumber> contractsPhoneNumbers = contractPhoneNumberRepository.findMonthlyCost(phoneNumber);
            fullCostInvoiceUser += contractsPhoneNumbers != null ? contractsPhoneNumbers.stream().mapToDouble(e -> e.getContracts().getMonthlyCost()).sum() : 0;

        }
        return fullCostInvoiceUser;
    }


  /*  public Invoices changeInvoiceStatus(String invoiceNumber){
        invoicesRepository.findByInvoiceNumber(invoiceNumber).

        return
    }*/

}
