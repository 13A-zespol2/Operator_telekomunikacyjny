package psk.phone.operator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.repository.ContractsRepository;
import psk.phone.operator.database.repository.PhoneNumberRepository;

import java.util.Optional;

public class PhoneNumberGeneratorTest {

    private PasswordEncoder passwordEncoder;
    private PhoneNumberRepository phoneNumberRepository;
    private BalanceNumberService balanceNumberService;
    private ContractsRepository contractsRepository;
    private PhoneNumberGeneratorService phoneNumberGeneratorService;


    @BeforeEach
    void setUp() {
        phoneNumberRepository = Mockito.mock(PhoneNumberRepository.class);
        balanceNumberService = Mockito.mock(BalanceNumberService.class);
        contractsRepository = Mockito.mock(ContractsRepository.class);
        this.passwordEncoder = Mockito.mock(PasswordEncoder.class);
        this.phoneNumberGeneratorService = new PhoneNumberGeneratorService(phoneNumberRepository, balanceNumberService, contractsRepository, passwordEncoder);
    }

    @Test
    void ifUpdatingPinGood() {
        //given
        String pin = "123";
        PhoneNumber phoneNumber = new PhoneNumber("123123123", pin);
        Mockito.when(phoneNumberRepository.findByNumber(phoneNumber.getNumber())).thenReturn(Optional.of(phoneNumber));
        String expectedPassword = passwordEncoder.encode(pin);
        Mockito.when(passwordEncoder.encode(pin)).thenReturn(expectedPassword);
        //when
        phoneNumberGeneratorService.updatePinForNumber(phoneNumber.getNumber(), pin);

        //then

        PhoneNumber expectedPhoneNumberPin = new PhoneNumber("123123123", expectedPassword);
        Mockito.verify(phoneNumberRepository, Mockito.times(1)).save(Mockito.eq(expectedPhoneNumberPin));


    }


}
