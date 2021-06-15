package psk.phone.operator.transport.converter;

import psk.phone.operator.database.entities.Package;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.enumeration.PackageType;
import psk.phone.operator.transport.dto.PackageDto;
import psk.phone.operator.transport.dto.UserDto;


/**
 * Klasa odpowiedzialna za konwersję obiektów.
 */
public class PackageConverter {

    /**
     * Metoda odpowiedzialna za konwersję obiektu klasy Package do obiektu klasy PackageDto.
     */
    public static PackageDto toDto (Package aPackage){
        return PackageDto.builder().namePackage(aPackage.getNamePackage())
                .description(aPackage.getDescription())
                .numberOfMinutes(aPackage.getNumberOfMinutes())
                .numberOfInternet(aPackage.getNumberOfInternet())
                .numberOfSms(aPackage.getNumberOfSms())
                .price(aPackage.getPrice())
                .packageType(aPackage.getPackageType().toString())
                .build();
    }
}
