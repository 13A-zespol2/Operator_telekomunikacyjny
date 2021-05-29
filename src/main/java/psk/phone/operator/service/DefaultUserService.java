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
import psk.phone.operator.transport.converter.UserPhoneNumberConverter;
import psk.phone.operator.transport.dto.UserDto;
import psk.phone.operator.transport.dto.UserPhoneDto;

import javax.management.openmbean.OpenDataException;

@Service
public class DefaultUserService {
    private final UserRepository userRepository;
    private final UserPhoneNumberRepository userPhoneNumberRepository;
    private final PasswordEncoder passwordEncoder;
    private final PhoneNumberGeneratorService phoneNumberGeneratorService;
    private final PackageService packageService;

    @Autowired
    public DefaultUserService(UserRepository userRepository, UserPhoneNumberRepository userPhoneNumberRepository, PasswordEncoder passwordEncoder, PhoneNumberGeneratorService phoneNumberGeneratorService, PackageService packageService) {
        this.userRepository = userRepository;
        this.userPhoneNumberRepository = userPhoneNumberRepository;
        this.passwordEncoder = passwordEncoder;
        this.phoneNumberGeneratorService = phoneNumberGeneratorService;
        this.packageService = packageService;
    }


    public UserPhoneDto registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException, OpenDataException {
        User user;
        try {
            user = emailExist(userDto.getEmail());
        } catch (UserAlreadyExistException e) {

            String password = userDto.getPassword();
            userDto.setPassword(passwordEncoder.encode(password));
            user = userRepository.save(UserConverter.toEntity(userDto));
        }
        PhoneNumber phoneNumber = phoneNumberGeneratorService.generatePhoneNumberForUser();

        return UserPhoneNumberConverter.toDto(registerUserNumber(user, phoneNumber));
    }


    public User loginUser(UserDto userDto) throws UserAlreadyExistException {
        try {
            User user = emailExist(userDto.getEmail());
            if (user != null) {
                String password = userDto.getPassword();

                if (!passwordEncoder.matches(password, user.getPassword())) {
                    throw new UserPasswordException("Password user " + user.getEmail() + " incorrectly");
                }
            }
            return user;
        } catch (UserAlreadyExistException e) {
            return null;
        }

    }


    public User processOAuthPostLogin(UserDto userDto) {
        try {
            return emailExist(userDto.getEmail());
        } catch (UserAlreadyExistException e) {
            User user = userRepository.save(UserConverter.toEntity(userDto));
            String password = userDto.getPassword();
            userDto.setPassword(passwordEncoder.encode(password));
            return user;
        }
    }

    private User emailExist(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserAlreadyExistException("There is an account with that email address: " + email));
    }


    private UserPhoneNumber registerUserNumber(User user, PhoneNumber phoneNumber) throws OpenDataException {
        UserPhoneNumber save = userPhoneNumberRepository.save(new UserPhoneNumber(user, phoneNumber));
        packageService.registerNewUserPackage(save.getPhoneNumber());
        return save;
    }


}
