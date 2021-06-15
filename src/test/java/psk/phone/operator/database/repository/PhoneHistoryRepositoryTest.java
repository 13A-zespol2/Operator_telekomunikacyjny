package psk.phone.operator.database.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import psk.phone.operator.database.entities.PhoneNumber;

@SpringBootTest
class PhoneHistoryRepositoryTest {

    @Autowired
    private PhoneHistoryRepository phoneHistoryRepository;
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Test
    public void queryTest() throws Exception {

        PhoneNumber byIdPhoneNumber = phoneNumberRepository.findByIdPhoneNumber(1L);

        // ArrayList<PhoneHistory> phoneHistories = phoneHistoryRepository.phoneCallHistoryToInvoice(byIdPhoneNumber, Date.valueOf("2021-05-01"));

        System.out.println("123");

    }
}