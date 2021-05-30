package psk.phone.operator.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPhoneDto {
    private UserDto userDto;
    private String phoneNumber;

    public UserPhoneDto(UserDto userDto, String phoneNumber) {
        this.userDto = userDto;
        this.phoneNumber = phoneNumber;
    }
}
