package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.PhoneHistory;
import psk.phone.operator.database.entities.SmsHistory;

import java.util.List;
import java.util.Optional;

@Repository
public interface SmsHistoryRepository extends JpaRepository<SmsHistory,Long> {

   @Query("SELECT s.idSmsHistory FROM SmsHistory s, Invoices i WHERE s.dateSms > i.dateInvoice")
   List<SmsHistory> smsHistoryToInvoice();
}
