package psk.phone.operator.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvoice;
    private Date dateInvoice;
    @OneToMany
    private List<PhoneHistory> phoneHistoryList;
    @OneToMany
    private List<SmsHistory> smsHistoryList;
    private Double price;
    private String statusInvoice;
    @ManyToOne
    private User user;

}
