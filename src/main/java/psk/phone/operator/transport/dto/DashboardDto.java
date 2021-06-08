package psk.phone.operator.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import psk.phone.operator.database.entities.Contracts;
import psk.phone.operator.database.entities.Invoices;

import java.util.List;

@Data
@NoArgsConstructor
public class DashboardDto {
    private List<String> phoneNumberList;
    private Invoices invoices;


    public DashboardDto(List<String> byIdUserPhoneNumber, Invoices invoices) {
     this.phoneNumberList = byIdUserPhoneNumber;
        this.invoices = invoices;
    }
}
