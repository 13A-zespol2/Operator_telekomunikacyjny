package psk.phone.operator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.repository.UserPhoneNumberRepository;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.transport.converter.UserConverter;
import psk.phone.operator.transport.dto.UserDto;

import java.util.Optional;

class DefaultUserServiceTest {

    private DefaultUserService defaultUserService;
    private UserRepository userRepository;
    private UserPhoneNumberRepository userPhoneNumberRepository;
    private PasswordEncoder passwordEncoder;
    private PhoneNumberGeneratorService phoneNumberGeneratorService;
    private ContractService contractService;

    @BeforeEach
    void setUp() {
        this.userRepository = Mockito.mock(UserRepository.class);
        this.userPhoneNumberRepository = Mockito.mock(UserPhoneNumberRepository.class);
        this.passwordEncoder = Mockito.mock(PasswordEncoder.class);
        this.phoneNumberGeneratorService = Mockito.mock(PhoneNumberGeneratorService.class);
        this.phoneNumberGeneratorService = Mockito.mock(PhoneNumberGeneratorService.class);
        this.contractService = Mockito.mock(ContractService.class);
        this.defaultUserService = new DefaultUserService(userRepository, userPhoneNumberRepository, passwordEncoder, phoneNumberGeneratorService, contractService);
    }

    @Test
    void registerNewUserAccount() {
        //given
        String password = "haslo";
        User user = new User(null, "Jan", "Kowalski", "jankowalski@gmail.com",password );
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        UserDto userDto = UserConverter.toDto(user);
        String expectedPassword = passwordEncoder.encode(password);
        Mockito.when(passwordEncoder.encode(password)).thenReturn(expectedPassword);
        //when
        defaultUserService.registerNewUserAccount(userDto);
        //then
        User expectedUser = new User(null, "Jan", "Kowalski", "jankowalski@gmail.com", expectedPassword);
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.eq(expectedUser));
    }

    @Test
    void registerExistsUserAccount() {
        //given
        String password = "haslo";
        User user = new User(null, "Jan", "Kowalski", "jankowalski@gmail.com", "haslo");
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        UserDto userDto = UserConverter.toDto(user);
        String expectedPassword = passwordEncoder.encode(password);
        Mockito.when(passwordEncoder.encode(password)).thenReturn(expectedPassword);
        //when

        defaultUserService.registerNewUserAccount(userDto);
        //then

    }

    @Test
    void loginUser() {
    }

    @Test
    void loginGoogleUser() {
    }
}