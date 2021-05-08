package psk.phone.operator.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginRestController {
    private final UserRepository userRepository;

    @GetMapping(path = "/{email}")
    public ResponseEntity<User> getLogin(@PathVariable String email) {
        System.out.println("sda");
        Optional<User> byEmail = userRepository.findByEmail(email);

        if (!byEmail.isEmpty()) {
            User user = byEmail.get();
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
}
