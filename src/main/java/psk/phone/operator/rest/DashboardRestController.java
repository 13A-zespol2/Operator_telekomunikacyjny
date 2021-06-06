package psk.phone.operator.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.entities.UserPhoneNumber;
import psk.phone.operator.database.repository.UserPhoneNumberRepository;
import psk.phone.operator.transport.dto.DashboardDto;
import psk.phone.operator.transport.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dashboard")
public class DashboardRestController {
    private UserPhoneNumberRepository userPhoneNumberRepository;

    @Autowired
    public DashboardRestController(UserPhoneNumberRepository userPhoneNumberRepository) {
        this.userPhoneNumberRepository = userPhoneNumberRepository;
    }

    @PostMapping("/{email}")
    public ResponseEntity<String> getAllRents(@PathVariable String email) {

        return ResponseEntity.ok("dsaasd");
    }

    @PostMapping("/changePin/{number}")
    public ResponseEntity<?> changePinForNumber(@PathVariable String number) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/registerNewNumber")
    public ResponseEntity<?> registerNewNumber(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getOtherNumbers")
    public ResponseEntity<?> getOtherNumbers(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().build();
    }


    private DashboardDto loadDataToDashboard(User user) {
        List<UserPhoneNumber> dashboardDto = userPhoneNumberRepository.findByUser(user);
        List<String> collectPhones = dashboardDto.stream().map(e -> e.getPhoneNumber().getNumber()).collect(Collectors.toList());

        return new DashboardDto(collectPhones, null, null);
    }


    //TODO PAYPAL

}
