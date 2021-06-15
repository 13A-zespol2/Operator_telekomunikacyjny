package psk.phone.operator.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Klasa transportowa dla klasy UserPhone.
 */
@Data
@NoArgsConstructor
public class UserPhoneDto {
    private UserDto userDto;
    private String phoneNumber;
}
