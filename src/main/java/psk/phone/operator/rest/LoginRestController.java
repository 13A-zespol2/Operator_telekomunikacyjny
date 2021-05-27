package psk.phone.operator.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.service.DefaultUserService;
import psk.phone.operator.transport.dto.UserDto;

@RestController
@RequiredArgsConstructor
public class LoginRestController {
    private DefaultUserService defaultUserService;

    @Autowired
    public LoginRestController(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity loginUser(@RequestBody UserDto user) {
        User user1 = defaultUserService.loginUser(user);
        return ResponseEntity.ok(ResponseEntity.ok());
    }

}
