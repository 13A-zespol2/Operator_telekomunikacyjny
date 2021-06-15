package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.NumberBalance;
import psk.phone.operator.database.entities.PhoneNumber;

import java.util.Optional;


/**
 * Interfejs odpowiadający za dostęp do tabeli ,,number_balance" w bazie danych.
 */
@Repository
public interface NumberBalanceRepository extends JpaRepository<NumberBalance, Long> {
    Optional<NumberBalance> findByPhoneNumber(PhoneNumber phoneNumber);

}
