package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.SmsHistory;
@Repository
public interface SmsHistoryRepository extends JpaRepository<SmsHistory,Long> {
}
