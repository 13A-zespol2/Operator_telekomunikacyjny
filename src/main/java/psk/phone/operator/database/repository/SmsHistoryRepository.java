package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.PhoneHistory;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.SmsHistory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SmsHistoryRepository extends JpaRepository<SmsHistory,Long> {

   @Query("SELECT COUNT(s.idSmsHistory) AS NumberOfMsg FROM SmsHistory s WHERE s.phoneNumberSender = ?1 and s.dateSms > ?2")
   Long smsHistoryToInvoice(PhoneNumber phoneNumber, Date smsDate);
}
