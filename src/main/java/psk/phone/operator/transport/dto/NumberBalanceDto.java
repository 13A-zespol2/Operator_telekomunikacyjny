package psk.phone.operator.transport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import psk.phone.operator.database.entities.PhoneNumber;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NumberBalanceDto {
    private PhoneNumber phoneNumber;
    private double balanceAccount;
    private int balanceInternet;
    private int balanceMinutes;
    private int smsBalance;
}
