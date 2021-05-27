package psk.phone.operator.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.service.DefaultUserService;
import psk.phone.operator.transport.converter.UserConverter;
import psk.phone.operator.transport.dto.UserDto;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegisterRestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DefaultUserService defaultUserService;

    @PostMapping(value = "/register")
    public ResponseEntity saveUser(@RequestBody UserDto user) throws Exception {
        User user1 = defaultUserService.registerNewUserAccount(user);
        UserDto userDto = UserConverter.toDto(user1);
        return ResponseEntity.ok(userDto);
    }


}
