package psk.phone.operator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import psk.phone.operator.database.entities.NumberBalance;
import psk.phone.operator.database.entities.PhoneHistory;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.repository.*;
import psk.phone.operator.service.InvoiceService;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public class InvoiceServiceTest {

    @Autowired
    private InvoicesRepository invoicesRepository;

  /*  @Test
    public void ifGenerateGoodInvoiceNumber(){
        InvoiceService invoiceService = new InvoiceService();

        String s1 = invoiceService.generateInvoiceNumber();

        assertEquals(s1, "OP/28/05/2021");

    }*/
    @Test
    public void queryTest(){
        Integer invNr = invoicesRepository.allInvoices();

        System.out.println(invNr);
    }

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private SmsHistoryRepository smsHistoryRepository;
    @Autowired
    private PhoneHistoryRepository phoneHistoryRepository;


    @Test
    public void ifGenerateInvoice(){
        PhoneNumber byIdPhoneNumber = phoneNumberRepository.findByIdPhoneNumber(1L);
        Long smsHistory = smsHistoryRepository.smsHistoryToInvoice(byIdPhoneNumber, Date.valueOf("2021-04-30"));
        Long minutesHistory = phoneHistoryRepository.phoneCallMinute(byIdPhoneNumber, Date.valueOf("2021-04-30"));

        double invoiceTotalPrice;
        double smsPrice;
        double minutePrice;




    }
}
