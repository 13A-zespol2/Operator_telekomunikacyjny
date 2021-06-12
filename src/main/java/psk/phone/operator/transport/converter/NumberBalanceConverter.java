package psk.phone.operator.transport.converter;

import psk.phone.operator.database.entities.NumberBalance;
import psk.phone.operator.transport.dto.NumberBalanceDto;

public class    NumberBalanceConverter {
    public static NumberBalanceDto toDto(NumberBalance numberBalance) {
        return NumberBalanceDto.builder().smsBalance(numberBalance.getSmsBalance())
                .balanceInternet(numberBalance.getBalanceInternet())
                .balanceMinutes(numberBalance.getBalanceMinutes())
                .balanceAccount(numberBalance.getBalanceAccount())
                .phoneNumber(numberBalance.getPhoneNumber())
                .build();
    }


}
