package psk.phone.operator.database.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import psk.phone.operator.database.entities.PhoneHistory;
import psk.phone.operator.database.entities.PhoneNumber;

import javax.ws.rs.core.Application;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneHistoryRepositoryTest {

    @Autowired
    private PhoneHistoryRepository phoneHistoryRepository;
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Test
    public void queryTest() throws Exception {

        PhoneNumber byIdPhoneNumber = phoneNumberRepository.findByIdPhoneNumber(1L);

        ArrayList<PhoneHistory> phoneHistories = phoneHistoryRepository.phoneCallHistoryToInvoice(byIdPhoneNumber, Date.valueOf("2021-05-01"));


        System.out.println("123");


    }
}