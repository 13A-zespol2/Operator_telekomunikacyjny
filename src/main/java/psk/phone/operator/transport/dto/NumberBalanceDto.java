package psk.phone.operator.transport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import psk.phone.operator.database.entities.PhoneNumber;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NumberBalanceDto {
    private PhoneNumber phoneNumber;
    private Double balanceAccount;
    private Double balanceInternet;
    private Double balanceMinutes;
    private int smsBalance;
}
