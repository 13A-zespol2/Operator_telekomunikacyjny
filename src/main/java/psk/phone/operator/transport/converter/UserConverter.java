package psk.phone.operator.transport.converter;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.transport.dto.UserDto;

public class UserConverter {


    public static User toEntity(UserDto dto) {
        return new User(dto.getName(), dto.getSurname(), dto.getEmail(), dto.getPassword());
    }

    public static User updateEntity(Long id, UserDto dto) {
        return new User(id, dto.getName(), dto.getSurname(), dto.getEmail(), dto.getPassword());
    }

    public static UserDto toDto(User user) {
        return new UserDto(user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
    }

    public static UserDto toDtoGoogle(DefaultOidcUser user) {
        return new UserDto(user.getName(), user.getFamilyName(), user.getEmail(), null);
    }

}