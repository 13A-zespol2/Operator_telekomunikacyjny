package psk.phone.operator.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import psk.phone.operator.database.entities.Contracts;
import psk.phone.operator.database.entities.Invoices;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.UserPhoneNumber;

import java.util.List;

@Data
@NoArgsConstructor
public class DashboardDto {
    private List<PhoneNumber> phoneNumberList;
    private Contracts contracts;
    private Invoices invoices;


    public DashboardDto(List<PhoneNumber> byIdUserPhoneNumber, Invoices invoices, Contracts contracts) {
     this.phoneNumberList = byIdUserPhoneNumber;
        this.contracts = contracts;
        this.invoices = invoices;
    }
}
