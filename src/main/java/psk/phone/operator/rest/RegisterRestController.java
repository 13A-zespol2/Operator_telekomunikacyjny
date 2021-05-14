package psk.phone.operator.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import psk.phone.operator.service.JwtUserDetailsService;
import psk.phone.operator.transport.dto.UserDto;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegisterRestController {
    @Autowired
    private JwtUserDetailsService userDetailsService;

   /* @PostMapping(path = "/register")
    public ResponseEntity<User> registerUser(HttpServletRequest request) {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User(name, surname, email, password);
        User save;
        try {
            save = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            log.error("Email jest w bazie");
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok().build();
    }*/


    @PostMapping(value = "/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }


}
