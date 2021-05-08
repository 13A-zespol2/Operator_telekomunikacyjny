package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.phone.operator.database.entities.Contract;

public interface ContractRepository extends JpaRepository<Contract,Long> {
}
