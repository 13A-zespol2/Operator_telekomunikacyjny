package psk.phone.operator.transport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import psk.phone.operator.database.entities.Contract;
import psk.phone.operator.database.entities.Invoices;

import java.util.List;

@Data
@NoArgsConstructor
public class DashboardDto {
    private List<String> phoneNumberList;
    private Contract contract;
    private Invoices invoices;


    public DashboardDto(List<String> byIdUserPhoneNumber, Invoices invoices, Contract contract) {
     this.phoneNumberList = byIdUserPhoneNumber;
        this.contract = contract;
        this.invoices = invoices;
    }
}
