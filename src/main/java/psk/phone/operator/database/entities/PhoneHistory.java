package psk.phone.operator.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PhoneHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPhoneHistory;
    @ManyToOne
    private PhoneNumber phoneNumberCaller;
    @ManyToOne
    private PhoneNumber phoneNumberReceiver;
    private String status;
    private Date dateCall;

}
