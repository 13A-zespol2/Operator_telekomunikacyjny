package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import psk.phone.operator.database.entities.PhoneHistory;
import psk.phone.operator.database.entities.PhoneNumber;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PhoneHistoryRepository extends JpaRepository<PhoneHistory, Long> {

    @Query("SELECT p FROM PhoneHistory p WHERE p.dateCall > ?2 and p.phoneNumberCaller = ?1")
    ArrayList<PhoneHistory> phoneCallHistoryToInvoice(PhoneNumber phoneNumber, Date phoneDateCall);



}