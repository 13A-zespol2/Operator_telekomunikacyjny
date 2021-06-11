package psk.phone.operator.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.entities.UserPhoneNumber;
import psk.phone.operator.database.repository.UserPhoneNumberRepository;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.service.BalanceNumberService;
import psk.phone.operator.service.DefaultUserService;
import psk.phone.operator.service.PhoneNumberGeneratorService;
import psk.phone.operator.transport.converter.UserConverter;
import psk.phone.operator.transport.dto.DashboardDto;
import psk.phone.operator.transport.dto.NumberBalanceDto;
import psk.phone.operator.transport.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dashboard")
public class DashboardRestController {
    private UserPhoneNumberRepository userPhoneNumberRepository;
    private UserRepository userRepository;
    private PhoneNumberGeneratorService phoneNumberGeneratorService;
    private BalanceNumberService balanceNumberService;
    private DefaultUserService defaultUserService;

    @Autowired
    public DashboardRestController(UserPhoneNumberRepository userPhoneNumberRepository, UserRepository userRepository, PhoneNumberGeneratorService phoneNumberGeneratorService, BalanceNumberService balanceNumberService, DefaultUserService defaultUserService) {
        this.userPhoneNumberRepository = userPhoneNumberRepository;
        this.userRepository = userRepository;
        this.phoneNumberGeneratorService = phoneNumberGeneratorService;
        this.balanceNumberService = balanceNumberService;
        this.defaultUserService = defaultUserService;
    }

    @PostMapping
    public ResponseEntity<DashboardDto> getDataToDashboard(@RequestBody UserDto userDto) {
        Optional<User> userByEmail = userRepository.findByEmail(userDto.getEmail());
        if (userByEmail.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<UserPhoneNumber> dashboardDto = userPhoneNumberRepository.findByUser(userByEmail.get());
        List<String> collectPhones = dashboardDto.stream().map(e -> e.getPhoneNumber().getNumber()).collect(Collectors.toList());

        List<PhoneNumber> collect = dashboardDto.stream().map(UserPhoneNumber::getPhoneNumber).collect(Collectors.toList());
        List<NumberBalanceDto> balanceForAllNumbers = balanceNumberService.findBalanceForAllNumbers(collect);

        return ResponseEntity.ok(new DashboardDto(collectPhones, null));
    }


    @PostMapping("/registerNewNumber")
    public ResponseEntity<?> registerNewNumber(@RequestBody UserDto userDto) {
        User byIdUser = userRepository.findByIdUser(UserConverter.toEntity(userDto).getIdUser());
        if (userPhoneNumberRepository.countByUser(byIdUser) < 3) {
            UserPhoneNumber userPhoneNumber = defaultUserService.registerUserNumber(byIdUser);
            return ResponseEntity.ok(userPhoneNumber);
        }
        return ResponseEntity.badRequest().build();
    }


    //TODO PAYPAL

}
