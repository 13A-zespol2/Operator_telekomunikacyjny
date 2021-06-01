package psk.phone.operator.rest;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.service.DefaultUserService;
import psk.phone.operator.transport.converter.UserConverter;
import psk.phone.operator.transport.dto.UserDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.Collections;

import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.CLIENT_ID;

@RestController
@RequiredArgsConstructor
public class LoginRestController {
    @Autowired
    private Environment env;
    @Autowired
    private DefaultUserService defaultUserService;
    @Value( "${google.client-id}" )
    private String jdbcUrl;
    @PostMapping(value = "/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserDto user) {
        User user1 = defaultUserService.loginUser(user);
        return ResponseEntity.ok(UserConverter.toDto(user1));
    }


    @GetMapping(value = "/googleRegister/{idToken}")
    public void loginUseer(@PathVariable String idToken) throws GeneralSecurityException, IOException {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList("google-client-id"))
                .build();
        env.getProperty("google.client-id");
        GoogleIdToken idTokenDecrypt = verifier.verify(idToken);
        if (idTokenDecrypt != null) {
            GoogleIdToken.Payload payload = idTokenDecrypt.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            // Use or store profile information
            // ...

        } else {
            System.out.println("Invalid ID token.");
        }

    }




}
