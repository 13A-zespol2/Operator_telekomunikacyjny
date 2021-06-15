package psk.phone.operator.service;

import org.springframework.stereotype.Service;
import psk.phone.operator.database.entities.Contracts;
import psk.phone.operator.database.entities.NumberBalance;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.repository.NumberBalanceRepository;
import psk.phone.operator.transport.converter.NumberBalanceConverter;
import psk.phone.operator.transport.dto.NumberBalanceDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Klasa zarządzająca stanem konta użytkownika.
 */
@Service
public class BalanceNumberService {

    private final NumberBalanceRepository numberBalanceRepository;

    public BalanceNumberService(NumberBalanceRepository numberBalanceRepository) {
        this.numberBalanceRepository = numberBalanceRepository;
    }


    /**
     * Metoda odpowiedzialna za ustawienie pakietu (kontraktu) startowego dla każdego użytkownika bezpośrednio
     * po rejestracji.
     * @param phoneNumber1
     * @param contracts
     */
    public void addDataFromContractToAccount(PhoneNumber phoneNumber1, Contracts contracts) {
        NumberBalance numberBalanceBuilder = NumberBalance.builder()
                .balanceAccount(0.0)
                .smsBalance(contracts.getContractSms())
                .phoneNumber(phoneNumber1)
                .balanceInternet(contracts.getContractsInternet())
                .balanceMinutes(contracts.getContractMinutes()).build();

        numberBalanceRepository.save(numberBalanceBuilder);
    }


    /**
     * Metoda odpowiedzialna za pobranie danych dotyczących numerów danego użytkownika i zwrócenie stanu danych
     * dla wszystkich numerów danego użytkownika.
     * @param phoneNumbers
     * @return
     */
    public List<NumberBalanceDto> findBalanceForAllNumbers(List<PhoneNumber> phoneNumbers) {
        return phoneNumbers.stream().map(numberBalanceRepository::findByPhoneNumber)
                .filter(Optional::isPresent).map(Optional::get)
                .map(NumberBalanceConverter::toDto)
                .collect(Collectors.toList());
    }
}
