package psk.phone.operator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.repository.UserRepository;
import psk.phone.operator.transport.dto.UserDto;
import psk.phone.operator.transport.converter.UserConverter;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private  PasswordEncoder bcryptEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            User user1 = user.get();
            return new org.springframework.security.core.userdetails.User(user1.getEmail(), user1.getPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException("User not found with email: " + email);

    }

    public User save(UserDto userDto) {
        String password = userDto.getPassword();
        userDto.setPassword(bcryptEncoder.encode(password));


        return userRepository.save(UserConverter.toEntity(userDto));
    }

}


