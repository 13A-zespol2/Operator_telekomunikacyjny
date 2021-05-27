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


    public UserPhoneDto registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
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

        User user = emailExist(userDto.getEmail());

        if (user != null) {
            String password = userDto.getPassword();

            if (!passwordEncoder.matches(password, userDto.getPassword())) {
                throw new UserPasswordException("Password user " + user.getEmail() + " incorrectly");
            }
        }
        return user;
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


    private UserPhoneNumber registerUserNumber(User user, PhoneNumber phoneNumber) {
        return userPhoneNumberRepository.save(new UserPhoneNumber(user, phoneNumber));
    }


}
