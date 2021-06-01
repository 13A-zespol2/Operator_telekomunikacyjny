package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.Contracts;
import psk.phone.operator.database.entities.Package;
@Repository
public interface ContractsRepository  extends JpaRepository<Contracts, Long> {
}
