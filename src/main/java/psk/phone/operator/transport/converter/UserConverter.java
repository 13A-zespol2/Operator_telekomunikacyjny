package psk.phone.operator.transport.converter;

import psk.phone.operator.database.entities.User;
import psk.phone.operator.transport.dto.UserDto;


/**
 * Klasa odpowiedzialna za konwersję obiektów.
 */
public class UserConverter {

    /**
     * Metoda odpowiedzialna za konwersję obiektu klasy UserDto do obiektu klasy User.
     */
    public static User toEntity(UserDto dto) {
        return new User(dto.getName(), dto.getSurname(), dto.getEmail(), dto.getPassword());
    }

    /**
     * Metoda odpowiedzialna za konwersję obiektu klasy User do obiektu klasy UserDto.
     */
    public static UserDto toDto(User user) {
        return new UserDto(user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
    }
}
