package psk.phone.operator.transport.converter;

import psk.phone.operator.database.entities.Package;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.enumeration.PackageType;
import psk.phone.operator.transport.dto.PackageDto;
import psk.phone.operator.transport.dto.UserDto;

public class PackageConverter {

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

    public static Package toEntity(PackageDto packageDto) {
        return new Package(packageDto.getNamePackage(), packageDto.getNumberOfSms(), packageDto.getNumberOfMinutes(),
                packageDto.getNumberOfInternet(), packageDto.getPrice(), packageDto.getDescription(), PackageType.valueOf(packageDto.getPackageType()));
    }

}
