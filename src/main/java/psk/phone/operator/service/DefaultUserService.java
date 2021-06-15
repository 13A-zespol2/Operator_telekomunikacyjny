package psk.phone.operator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import psk.phone.operator.config.error.ContractException;
import psk.phone.operator.config.error.NoSuchUserException;
import psk.phone.operator.config.error.UserAlreadyExistException;
import psk.phone.operator.config.error.UserPasswordException;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.entities.UserPhoneNumber;
import psk.phone.operator.database.repository.UserPhoneNumberRepository;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.transport.converter.UserConverter;
import psk.phone.operator.transport.dto.UserDto;

import java.util.Optional;


/**
 * Klasa zarządzająca autoryzacją użytkownika (logowanie, rejestracja, zabezpieczanie hasła).
 */
@Service
public class DefaultUserService {
    private final UserRepository userRepository;
    private final UserPhoneNumberRepository userPhoneNumberRepository;
    private final PasswordEncoder passwordEncoder;
    private final PhoneNumberGeneratorService phoneNumberGeneratorService;
    private final ContractService contractService;


    @Autowired
    public DefaultUserService(UserRepository userRepository, UserPhoneNumberRepository userPhoneNumberRepository, PasswordEncoder passwordEncoder, PhoneNumberGeneratorService phoneNumberGeneratorService, ContractService contractService) {
        this.userRepository = userRepository;
        this.userPhoneNumberRepository = userPhoneNumberRepository;
        this.passwordEncoder = passwordEncoder;
        this.phoneNumberGeneratorService = phoneNumberGeneratorService;
        this.contractService = contractService;
    }


    /**
     * Metoda odpowiedzialna za proces rejestracji nowego użytkownika.
     * @param userToRegister
     * @return
     * @throws UserAlreadyExistException
     */
    public boolean registerNewUserAccount(UserDto userToRegister) throws UserAlreadyExistException {
        Optional<User> byEmail = findByEmail(userToRegister.getEmail());
        if (byEmail.isPresent()) {
            throw new UserAlreadyExistException("User exists");
        }
        String password = userToRegister.getPassword();
        userToRegister.setPassword(encodePassword(password));
        User userFromDto = UserConverter.toEntity(userToRegister);
        User savedUser = userRepository.save(userFromDto);
        registerUserNumber(savedUser);
        return true;
    }


    /**
     * Metoda odpowiedzialna za logowanie użytkownika do aplikacji.
     * @param email
     * @param password
     * @return
     * @throws UserPasswordException
     * @throws NoSuchUserException
     */
    public User loginUser(String email, String password) throws UserPasswordException, NoSuchUserException {
        return findByEmail(email).map(u -> {

            if (!passwordEncoder.matches(password, u.getPassword())) {
                throw new UserPasswordException("Password user " + email + " incorrect");
            }
            return u;
        }).orElseThrow(() -> new NoSuchUserException("User not found"));
    }


    /**
     * Metoda odpowiedzialna za autoryzację użytkownika za pomocą Google.
     * @param user
     * @return
     */
    public User loginGoogleUser(User user) {

        Optional<User> userOptional = findByEmail(user.getEmail());

        if (userOptional.isEmpty()) {
            User save = userRepository.save(user);
            registerUserNumber(save);
            return save;
        }
        return userOptional.get();
    }


    /**
     * Metoda odpowiedzialna za znalezienie informacji, czy użytkownik z danym adresem e-mail istnieje już w bazie.
     * @param email
     * @return
     */
    private Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    /**
     * Metoda odpowiedzialna za przypisanie nowego numeru telefonu do danego użytkownika.
     * @param user
     * @return
     */
    public UserPhoneNumber registerUserNumber(User user) {
        PhoneNumber phoneNumber = null;
        try {

            phoneNumber = phoneNumberGeneratorService.generatePhoneNumberForUser();
            contractService.assignContractToPhoneNUmber(phoneNumber, 1L);
        } catch (ContractException e) {
            return null;
        }

        return userPhoneNumberRepository.save(new UserPhoneNumber(user, phoneNumber));
    }


    /**
     * Metoda odpowiedzialna za odkodowanie hasła użytkownika.
     * @param userFromBase
     * @return
     */
    private String encodePassword(String userFromBase) {
        return passwordEncoder.encode(userFromBase);
    }

}
