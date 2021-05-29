package psk.phone.operator.rest;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.service.DefaultUserService;
import psk.phone.operator.transport.dto.UserDto;
import psk.phone.operator.transport.dto.UserPhoneDto;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegisterRestController {
    @Autowired
    private DefaultUserService defaultUserService;

    @SneakyThrows
    @PostMapping(value = "/register")
    public ResponseEntity saveUser(@RequestBody UserDto user) {
        UserPhoneDto user1 = defaultUserService.registerNewUserAccount(user);
        System.out.println("dsa");
        return ResponseEntity.ok(ResponseEntity.ok());
    }


}
