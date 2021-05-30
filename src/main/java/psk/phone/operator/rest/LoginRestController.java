package psk.phone.operator.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.service.DefaultUserService;
import psk.phone.operator.transport.converter.UserConverter;
import psk.phone.operator.transport.dto.UserDto;

@RestController
@RequiredArgsConstructor
public class LoginRestController {

    @Autowired
    private DefaultUserService defaultUserService;

    @PostMapping(value = "/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserDto user) {
        User user1 = defaultUserService.loginUser(user);
        return ResponseEntity.ok(UserConverter.toDto(user1));
    }


}
