package psk.phone.operator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import psk.phone.operator.database.repository.PhoneHistoryRepository;
import psk.phone.operator.database.repository.PhoneNumberRepository;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.service.InvoiceService;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

public class InvoiceServiceTest {


    @Test
    public void ifGenerateGoodInvoiceNumber(){
        InvoiceService invoiceService = new InvoiceService();

        String s1 = invoiceService.generateInvoiceNumber();

        assertEquals(s1, "OP/28/05/2021");

    }

    @Autowired
    PhoneHistoryRepository phoneHistoryRepository;

    @Test
    public void ifRepositoryReturnGoodDate(){




    }
}
