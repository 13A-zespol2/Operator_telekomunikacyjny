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

    @GetMapping(value = "/generateNewNumber")
    public void loginUser(HttpSession session) {
        PhoneNumber phoneNumber = phoneNumberGeneratorService.generatePhoneNumberForUser();
        User user = new User("wojtek", "g", "wojtekgrelewicz@gmail.com", "qwe");
        Optional<User> byEmail = userRepository.findByEmail("wojtekgrelewicz@gmail.com");
        User user1 = byEmail.get();
        UserPhoneNumber save = userPhoneNumberRepository.save(new UserPhoneNumber(user1, phoneNumber));
        if(save != null)
        {
           // userPhoneNumberRepository.findByIdUserPhoneNumber(user1);
        }
    }






    @PutMapping("/{email}")
    public void editUser(@PathVariable String email) {
     /*   if (userRepository.existsById(id)) {
            return ResponseEntity.ok(userRepository.save(UserConverter.updateEntity(id, User)));
        } else {
            return ResponseEntity.notFound().build();
        }*/
    }


}
