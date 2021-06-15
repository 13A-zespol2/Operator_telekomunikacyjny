package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.Invoices;
import psk.phone.operator.database.entities.User;

import java.util.List;

/**
 * Interfejs odpowiadający za dostęp do tabeli ,,invoices" w bazie danych.
 */
@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Long> {


    @Query("SELECT COUNT(inv.idInvoice) FROM Invoices inv ")
    Integer allInvoices();

    List<Invoices> findAllByUser(User user);

    @Query("SELECT i FROM Invoices i WHERE i.invoiceNumber = ?1 ")
    Invoices findByInvoiceNumber(String number);

    List<Invoices> findOneInvoiceByUser(User user);


}
