package psk.phone.operator.database.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import psk.phone.operator.database.enumeration.InvoiceStatusEnum;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvoice;
    private String InvoiceNumber;

    private LocalDate dateInvoice;

    public Invoices(Long idInvoice, String invoiceNumber, LocalDate dateInvoice, Double price, InvoiceStatusEnum statusInvoice, User user) {
        this.idInvoice = idInvoice;
        InvoiceNumber = invoiceNumber;
        this.dateInvoice = dateInvoice;
        this.price = price;
        this.statusInvoice = statusInvoice;
        this.user = user;
    }

    @OneToMany
    private List<PhoneHistory> phoneHistoryList;
    @OneToMany
    private List<SmsHistory> smsHistoryList;
    private Double price;
    private InvoiceStatusEnum statusInvoice;
    @ManyToOne
    private User user;

}
