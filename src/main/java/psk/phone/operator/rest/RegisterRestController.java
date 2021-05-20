package psk.phone.operator.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.transport.converter.UserConverter;
import psk.phone.operator.transport.dto.UserDto;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegisterRestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/register")
    public String saveUser(@RequestBody UserDto user) throws Exception {
        passwordEncoder.encode(user.getPassword());
        userRepository.save(UserConverter.toEntity(user));
        return "Hello" + user.getEmail();
    }


}
