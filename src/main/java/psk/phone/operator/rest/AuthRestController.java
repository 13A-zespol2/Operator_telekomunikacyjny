package psk.phone.operator.rest;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psk.phone.operator.config.security.GoogleIdTokenVerifier;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.entities.UserPhoneNumber;
import psk.phone.operator.database.repository.UserPhoneNumberRepository;
import psk.phone.operator.service.DefaultUserService;
import psk.phone.operator.transport.converter.UserConverter;
import psk.phone.operator.transport.dto.DashboardDto;
import psk.phone.operator.transport.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthRestController {

    private DefaultUserService defaultUserService;
    private UserPhoneNumberRepository userPhoneNumberRepository;

    @Autowired
    public AuthRestController(DefaultUserService defaultUserService, UserPhoneNumberRepository userPhoneNumberRepository) {
        this.defaultUserService = defaultUserService;
        this.userPhoneNumberRepository = userPhoneNumberRepository;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<DashboardDto> loginBasicUser(@RequestBody UserDto user, HttpServletRequest request) {
        Optional<UserDto> user1 = defaultUserService.loginUser(UserConverter.toEntity(user));
        if (user1.isPresent()) {
            UserDto userDto = user1.get();
            request.getSession().setAttribute("loggedIn", userDto);
            return ResponseEntity.ok(loadDataToDashboard(UserConverter.toEntity(userDto)));
        }
        return ResponseEntity.notFound().build();
    }

    @SneakyThrows
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerNewUser(@RequestBody UserDto user) {
        Optional<UserDto> user1 = defaultUserService.registerNewUserAccount(UserConverter.toEntity(user));
        if (user1.isPresent())
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping(value = "/googleRegister/{idToken}")
    public ResponseEntity<DashboardDto> loginGoogleUser(HttpServletRequest request, @PathVariable String idToken) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList("1010076371628-49i4v1vkp465oee457u281tl5tksc417.apps.googleusercontent.com"))
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
            Optional<User> userDto = defaultUserService.loginGoogleUser(user);
            if (userDto.isPresent()) {
                request.getSession().setAttribute("loggedIn", UserConverter.toDto(userDto.get()));
                return ResponseEntity.ok(loadDataToDashboard(userDto.get()));
            }
        }
        return ResponseEntity.badRequest().build();
    }


    private DashboardDto loadDataToDashboard(User user) {
        List<UserPhoneNumber> dashboardDto = userPhoneNumberRepository.findByUser(user);
        List<String> collectPhones = dashboardDto.stream().map(e -> e.getPhoneNumber().getNumber()).collect(Collectors.toList());

        return new DashboardDto(collectPhones, null, null);


    }


}
