package psk.phone.operator.database.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.Invoices;
import psk.phone.operator.database.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Long> {


    @Query("SELECT COUNT(inv.idInvoice) FROM Invoices inv ")
    Integer allInvoices();

    List<Invoices> findAllByUser(User user);


    List<Invoices> findOneInvoiceByUser(User user);
}
