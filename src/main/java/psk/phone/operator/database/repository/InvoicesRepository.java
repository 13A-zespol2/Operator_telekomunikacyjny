package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.phone.operator.database.entities.Invoices;

public interface InvoicesRepository extends JpaRepository<Invoices, Long> {


}
