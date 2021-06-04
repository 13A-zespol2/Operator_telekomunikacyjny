package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.Invoices;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Long> {


    @Query("SELECT COUNT(inv.idInvoice) FROM Invoices inv ")
    Integer allInvoices();

}
