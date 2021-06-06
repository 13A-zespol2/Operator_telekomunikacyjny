package psk.phone.operator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import psk.phone.operator.database.entities.Package;
import psk.phone.operator.database.entities.*;
import psk.phone.operator.database.enumeration.InvoiceStatusEnum;
import psk.phone.operator.database.repository.ContractPhoneNumberRepository;
import psk.phone.operator.database.repository.InvoicesRepository;
import psk.phone.operator.database.repository.PurchasedPackagesRepository;
import psk.phone.operator.database.repository.UserPhoneNumberRepository;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;


class InvoiceServiceTest {

    private InvoiceService invoiceService;
    private UserPhoneNumberRepository userPhoneNumberRepository;
    private PurchasedPackagesRepository purchasedPackagesRepository;
    private ContractPhoneNumberRepository contractPhoneNumberRepository;
    private InvoicesRepository invoicesRepository;

    @BeforeEach
    void setUp() {
        System.out.println("setup");
        userPhoneNumberRepository = Mockito.mock(UserPhoneNumberRepository.class);
        purchasedPackagesRepository = Mockito.mock(PurchasedPackagesRepository.class);
        contractPhoneNumberRepository = Mockito.mock(ContractPhoneNumberRepository.class);
        invoicesRepository = Mockito.mock(InvoicesRepository.class);
        this.invoiceService = new InvoiceService(userPhoneNumberRepository, purchasedPackagesRepository, contractPhoneNumberRepository, invoicesRepository);
    }

    @Test
    public void test_generateInvoiceForOneUseSinglePhoneNumber() {
        //given
        LocalDate nowDate = LocalDate.now();
        User user = new User(null, "Jan", "Kowalski", "jankowalski@gmail.com", "asd");
        PhoneNumber phoneNumber = new PhoneNumber("123123123", null);
        UserPhoneNumber userPhoneNumber = new UserPhoneNumber(user, phoneNumber);

        Mockito.when(userPhoneNumberRepository.findByUser(user)).thenReturn(List.of(userPhoneNumber));
        Package aPackage = new Package(null, "start", 200, 2000, 200, 20);
        PurchasedPackages purchasedPackages = new PurchasedPackages(null, aPackage, phoneNumber, LocalDate.of(2021, nowDate.getMonth().getValue(), 20));
        LocalDate localDateStart = nowDate.minusDays(30);

        Mockito.when(purchasedPackagesRepository.findPurchasedPackagesByPhoneNumber(phoneNumber, localDateStart, nowDate)).thenReturn(List.of(purchasedPackages));
        Contract contract = new Contract(null, "start", 200, 2000, 200, 20);
        ContractsPhoneNumber contractsPhoneNumber = new ContractsPhoneNumber(contract, phoneNumber,LocalDate.of(2021, nowDate.getMonth().getValue(), 20));

        Mockito.when(contractPhoneNumberRepository.findMonthlyCost(phoneNumber)).thenReturn(List.of(contractsPhoneNumber));
        //when
        invoiceService.creatingInvoice(user);


        //then
        double expectedMonthlyCost = 40.0;

        DecimalFormat decimalFormat = new DecimalFormat("00");
        String expectedInvoiceNumber = "OP/1/" + decimalFormat.format(nowDate.getDayOfMonth()) + "/" + decimalFormat.format(nowDate.getMonth().getValue()) + "/" + nowDate.getYear();
        Invoices
                expectedInvoice = new Invoices(null, expectedInvoiceNumber, nowDate, expectedMonthlyCost, InvoiceStatusEnum.UNPAID, user);
        Mockito.verify(invoicesRepository, Mockito.times(1)).save(Mockito.eq(expectedInvoice));

    }

    @Test
    public void test_generateInvoiceForOneUserWithoutPackages() {
        //given
        User user = new User(null, "Jan", "Kowalski", "jankowalski@gmail.com", "asd");
        PhoneNumber phoneNumber = new PhoneNumber("123123123", null);
        UserPhoneNumber userPhoneNumber = new UserPhoneNumber(user, phoneNumber);

        Mockito.when(userPhoneNumberRepository.findByUser(Mockito.eq(user))).thenReturn(List.of(userPhoneNumber));

        Mockito.when(purchasedPackagesRepository.findPurchasedPackagesByPhoneNumber(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(List.of());
        Contract contract = new Contract(null, "start", 200, 2000, 200, 20);
        ContractsPhoneNumber contractsPhoneNumber = new ContractsPhoneNumber(contract, phoneNumber, LocalDate.now());

        Mockito.when(contractPhoneNumberRepository.findMonthlyCost(phoneNumber)).thenReturn(List.of(contractsPhoneNumber));
        //when
        invoiceService.creatingInvoice(user);
        //then
        double expectedMonthlyCost = 20.0;
        LocalDate nowDate = LocalDate.now();
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String expectedInvoiceNumber = "OP/1/" + decimalFormat.format(nowDate.getDayOfMonth()) + "/" + decimalFormat.format(nowDate.getMonth().getValue()) + "/" + nowDate.getYear();
        Invoices expectedInvoice = new Invoices(null, expectedInvoiceNumber, nowDate, expectedMonthlyCost, InvoiceStatusEnum.UNPAID, user);
        Mockito.verify(invoicesRepository, Mockito.times(1)).save(Mockito.eq(expectedInvoice));
    }


}