package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.phone.operator.database.entities.PhoneHistory;

public interface PhoneHistoryRepository extends JpaRepository<PhoneHistory, Long> {

}
