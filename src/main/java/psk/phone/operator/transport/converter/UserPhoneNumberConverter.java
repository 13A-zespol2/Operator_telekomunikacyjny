package psk.phone.operator.transport.converter;

import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.entities.UserPhoneNumber;
import psk.phone.operator.transport.dto.UserDto;
import psk.phone.operator.transport.dto.UserPhoneDto;

public class UserPhoneNumberConverter {

    /*public static UserPhoneNumber toEntity(UserPhoneDto dto) {
        return new UserPhoneNumber(dto.getPhoneNumber(), dto.);
    }*/

    public static User updateEntity(Long id, UserDto dto) {
        return new User(id, dto.getName(), dto.getSurname(), dto.getEmail(), dto.getPassword());
    }

    public static UserPhoneDto toDto(UserDto userDto, String phoneNumber) {
        return new UserPhoneDto(userDto, phoneNumber);
    }

    public static UserPhoneDto toDto(UserPhoneNumber registerUserNumber) {
        UserDto userDto = UserConverter.toDto(registerUserNumber.getUser());
        return new UserPhoneDto(userDto,registerUserNumber
                .getPhoneNumber()
                .getNumber());
    }
}
