package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.phone.operator.database.entities.NumberBalance;

public interface NumberBalanceRepository extends JpaRepository<NumberBalance, Long> {
}
