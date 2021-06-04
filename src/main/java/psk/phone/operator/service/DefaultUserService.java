package psk.phone.operator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import psk.phone.operator.config.error.UserAlreadyExistException;
import psk.phone.operator.config.error.UserPasswordException;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.entities.UserPhoneNumber;
import psk.phone.operator.database.repository.UserPhoneNumberRepository;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.transport.converter.UserConverter;
import psk.phone.operator.transport.dto.UserDto;

import javax.management.openmbean.OpenDataException;
import java.util.Optional;

@Service
public class DefaultUserService {
    private final UserRepository userRepository;
    private final UserPhoneNumberRepository userPhoneNumberRepository;
    private final PasswordEncoder passwordEncoder;
    private final PhoneNumberGeneratorService phoneNumberGeneratorService;


    @Autowired
    public DefaultUserService(UserRepository userRepository, UserPhoneNumberRepository userPhoneNumberRepository, PasswordEncoder passwordEncoder, PhoneNumberGeneratorService phoneNumberGeneratorService) {
        this.userRepository = userRepository;
        this.userPhoneNumberRepository = userPhoneNumberRepository;
        this.passwordEncoder = passwordEncoder;
        this.phoneNumberGeneratorService = phoneNumberGeneratorService;

    }

    //TODO nie ma dodawania numeru
    public Optional<UserDto> registerNewUserAccount(User userToRegister) throws UserAlreadyExistException {
        User user;
        try {
            user = emailExist(userToRegister.getEmail());
        } catch (UserAlreadyExistException e) {
            encodePassword(userToRegister);
            user = userRepository.save(userToRegister);
            try {
                registerUserNumber(user);
            } catch (OpenDataException openDataException) {
                return Optional.empty();
            }
        }
        return Optional.of(UserConverter.toDto(user));
    }

    public Optional<UserDto> loginUser(User user) throws UserAlreadyExistException {
        try {
            User user1 = emailExist(user.getEmail());
            String password = user.getPassword();
            if (!passwordEncoder.matches(password, user1.getPassword())) {
                throw new UserPasswordException("Password user " + user.getEmail() + " incorrectly");
            }
            return Optional.of(UserConverter.toDto(user1));
        } catch (UserAlreadyExistException e) {
            return Optional.empty();
        }
    }

    public Optional<User> loginGoogleUser(User user) {
        try {

            return Optional.of(emailExist(user.getEmail()));
        } catch (UserAlreadyExistException e) {
            User newGoogleUser = userRepository.save(user);
            try {
                registerUserNumber(newGoogleUser);
            } catch (OpenDataException openDataException) {
                return Optional.empty();
            }
            return Optional.of(user);
        }
    }

    private User emailExist(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserAlreadyExistException("There is an account with that email address: " + email));
    }

    private UserPhoneNumber registerUserNumber(User user) throws OpenDataException {
        PhoneNumber phoneNumber = phoneNumberGeneratorService.generatePhoneNumberForUser();
        return userPhoneNumberRepository.save(new UserPhoneNumber(user, phoneNumber));
    }

    private void encodePassword(User userFromBase) {
        userFromBase.setPassword(passwordEncoder.encode(userFromBase.getPassword()));
    }

}
