package psk.phone.operator.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.entities.UserPhoneNumber;
import psk.phone.operator.database.repository.UserPhoneNumberRepository;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.service.BalanceNumberService;
import psk.phone.operator.service.PhoneNumberGeneratorService;
import psk.phone.operator.transport.dto.NumberBalanceDto;
import psk.phone.operator.transport.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/numbers")
public class NumbersController {
    private UserPhoneNumberRepository userPhoneNumberRepository;
    private BalanceNumberService balanceNumberService;
    private UserRepository userRepository;
    private PhoneNumberGeneratorService phoneNumberGeneratorService;

    @Autowired
    public NumbersController(UserPhoneNumberRepository userPhoneNumberRepository, BalanceNumberService balanceNumberService, UserRepository userRepository, PhoneNumberGeneratorService phoneNumberGeneratorService) {
        this.userPhoneNumberRepository = userPhoneNumberRepository;
        this.balanceNumberService = balanceNumberService;
        this.userRepository = userRepository;
        this.phoneNumberGeneratorService = phoneNumberGeneratorService;
    }

    @PostMapping
    public ResponseEntity<List<NumberBalanceDto>> findAllUserNumber(@RequestBody UserDto userDto) {
        Optional<User> userByEmail = userRepository.findByEmail(userDto.getEmail());
        if (userByEmail.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<PhoneNumber> userPhoneNumbers = userPhoneNumberRepository.findByUser(userByEmail.get())
                .stream().map(UserPhoneNumber::getPhoneNumber)
                .collect(Collectors.toList());
        List<NumberBalanceDto> balanceForAllNumbers = balanceNumberService.findBalanceForAllNumbers(userPhoneNumbers);

        return ResponseEntity.ok(balanceForAllNumbers);
    }


    @PutMapping("/{number}/{pin}")
    public ResponseEntity<?> changePinForNumber(@PathVariable String pin, @PathVariable String number) {
        return phoneNumberGeneratorService.updatePinForNumber(number, pin) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }


}
