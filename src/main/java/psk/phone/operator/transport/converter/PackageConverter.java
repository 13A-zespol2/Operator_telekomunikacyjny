package psk.phone.operator.transport.converter;

import psk.phone.operator.database.entities.Package;
import psk.phone.operator.transport.dto.PackageDto;

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
}
