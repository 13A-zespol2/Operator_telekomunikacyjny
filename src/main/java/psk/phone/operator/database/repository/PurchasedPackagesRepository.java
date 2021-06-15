package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.PurchasedPackages;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Interfejs odpowiadający za dostęp do tabeli ,,purchased_packages" w bazie danych.
 */
@Repository
public interface PurchasedPackagesRepository extends JpaRepository<PurchasedPackages, Long> {

    @Query("SELECT pp FROM PurchasedPackages pp WHERE pp.phoneNumber = ?1 and pp.datePurchaseDate > ?2")
    ArrayList<PurchasedPackages> findPurchasedPackagesByPhoneNumber(PhoneNumber phoneNumbers, LocalDate mDate);


    @Query("SELECT pp FROM PurchasedPackages pp WHERE pp.phoneNumber = ?1 AND pp.datePurchaseDate > ?2 AND pp.datePurchaseDate < ?3")
    List<PurchasedPackages> findPurchasedPackagesByPhoneNumber(PhoneNumber phoneNumbers, LocalDate firstDay, LocalDate lastDay);

}
