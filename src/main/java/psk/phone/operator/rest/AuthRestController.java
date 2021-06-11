package psk.phone.operator.rest;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psk.phone.operator.config.error.NoSuchUserException;
import psk.phone.operator.config.error.UserPasswordException;
import psk.phone.operator.config.security.GoogleIdTokenVerifier;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.service.DefaultUserService;
import psk.phone.operator.transport.converter.UserConverter;
import psk.phone.operator.transport.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class AuthRestController {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;


    private DefaultUserService defaultUserService;


    @Autowired
    public AuthRestController(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }


    @PostMapping(value = "/login")
    public ResponseEntity<UserDto> loginBasicUser(@RequestBody UserDto user) {
        try {
            User user1 = defaultUserService.loginUser(user.getEmail(), user.getPassword());
            return ResponseEntity.ok(UserConverter.toDto(user1));
        } catch (NoSuchUserException e) {
            return ResponseEntity.notFound().build();
        } catch (UserPasswordException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping(value = "/register")
    public ResponseEntity<?> registerNewUser(@RequestBody UserDto userDto) {
        boolean user1 = defaultUserService.registerNewUserAccount(userDto);
        if (user1) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping(value = "/googleRegister/{idToken}")
    public ResponseEntity<UserDto> loginGoogleUser(HttpServletRequest request, @PathVariable String idToken) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(googleClientId))
                .build();
        GoogleIdToken idTokenDecrypt = verifier.verify(idToken);
        User user = null;
        if (idTokenDecrypt != null) {
            GoogleIdToken.Payload payload = idTokenDecrypt.getPayload();
            String email = payload.getEmail();
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");
            user = User.builder().email(email).name(givenName).surname(familyName).build();
        }
        if (user != null) {
            User user1 = defaultUserService.loginGoogleUser(user);
            return ResponseEntity.ok(UserConverter.toDto(user1));
        }
        return ResponseEntity.badRequest().build();
    }


}
