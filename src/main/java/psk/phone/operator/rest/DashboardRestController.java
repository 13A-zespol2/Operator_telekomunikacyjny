package psk.phone.operator.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.entities.UserPhoneNumber;
import psk.phone.operator.database.repository.UserPhoneNumberRepository;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.service.PhoneNumberGeneratorService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/dashboard")
public class DashboardRestController {
    private final PhoneNumberGeneratorService phoneNumberGeneratorService;
    private final UserPhoneNumberRepository userPhoneNumberRepository;
    private UserRepository userRepository;

    @Autowired
    public DashboardRestController(PhoneNumberGeneratorService phoneNumberGeneratorService, UserPhoneNumberRepository userPhoneNumberRepository, UserRepository user) {
        this.phoneNumberGeneratorService = phoneNumberGeneratorService;
        this.userPhoneNumberRepository = userPhoneNumberRepository;
        this.userRepository = user;
    }



}
