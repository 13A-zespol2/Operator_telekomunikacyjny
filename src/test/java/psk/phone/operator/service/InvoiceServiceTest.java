package psk.phone.operator.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import psk.phone.operator.database.entities.User;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class InvoiceServiceTest {


    @InjectMocks
    private InvoiceService invoiceService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void ifGenerateInvoice(){
        User user = new User(1L, "Karol", "Mik");
        invoiceService.creatingInvoice(user);
    }

}