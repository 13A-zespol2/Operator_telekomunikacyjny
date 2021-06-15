package psk.phone.operator.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Klasa transportowa dla klasy User.
 */
@Data
@NoArgsConstructor
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private String password;

    public UserDto(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
