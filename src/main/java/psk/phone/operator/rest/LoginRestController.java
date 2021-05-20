package psk.phone.operator.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import psk.phone.operator.transport.dto.UserDto;

import java.net.http.HttpResponse;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class LoginRestController {
    @PostMapping(value = "/login")
    public void saveUser(@RequestBody UserDto user) throws Exception {
        UserDto user1 = user;
        System.out.println("Sda");
    }

    @GetMapping("/htllo")
    public String sayHello()
    {
        return "tasd";
    }

    @PostMapping(value = "googleAuthlogin")
    public Principal user(Principal principal) {
        return principal;
    }
}
