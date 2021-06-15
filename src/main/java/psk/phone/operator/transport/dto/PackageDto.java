package psk.phone.operator.transport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Klasa transportowa dla klasy Package.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageDto {
    private String namePackage;
    private int numberOfSms;
    private int numberOfMinutes;
    private int numberOfInternet;
    private double price;
    private String description;
    private String packageType;
}
