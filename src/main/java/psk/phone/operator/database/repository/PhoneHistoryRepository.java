package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.PhoneHistory;
import psk.phone.operator.database.entities.PhoneNumber;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * Interfejs odpowiadający za dostęp do tabeli ,,phone_history" w bazie danych.
 */
@Repository
public interface PhoneHistoryRepository extends JpaRepository<PhoneHistory, Long> {

    @Query(value = "SELECT sum(TIME_TO_SEC(timediff(p.date_call_end, p.date_call))/60) AS CallMinute FROM operator.phone_history p WHERE p.date_call > ?2 and p.phone_number_caller_id_phone_number = ?1 ",nativeQuery = true )
    Long phoneCallMinute(PhoneNumber phoneNumber, Date dateCall);

}