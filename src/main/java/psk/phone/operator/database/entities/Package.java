package psk.phone.operator.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import psk.phone.operator.database.enumeration.PackageType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity

public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namePackage;
    private int numberOfSms;
    private int numberOfMinutes;
    private int numberOfInternet;
    private double price;
    private String description;
    private PackageType packageType;
}
