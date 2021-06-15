package psk.phone.operator.transport.converter;

import psk.phone.operator.database.entities.NumberBalance;
import psk.phone.operator.transport.dto.NumberBalanceDto;


/**
 * Klasa odpowiedzialna za konwersję obiektów.
 */
public class NumberBalanceConverter {

    /**
     * Metoda odpowiedzialna za konwersję obiektu klasy NumberBalance do obiektu klasy NumberBalanceDto.
     */
    public static NumberBalanceDto toDto(NumberBalance numberBalance) {
        return NumberBalanceDto.builder().smsBalance(numberBalance.getSmsBalance())
                .balanceInternet(numberBalance.getBalanceInternet())
                .balanceMinutes(numberBalance.getBalanceMinutes())
                .balanceAccount(numberBalance.getBalanceAccount())
                .phoneNumber(numberBalance.getPhoneNumber())
                .build();
    }


}
