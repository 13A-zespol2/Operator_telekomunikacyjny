package psk.phone.operator.service;

import org.springframework.stereotype.Service;
import psk.phone.operator.database.entities.Contracts;
import psk.phone.operator.database.entities.NumberBalance;
import psk.phone.operator.database.entities.Package;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.repository.NumberBalanceRepository;

import java.util.Optional;

@Service
public class BalanceNumberService {

    private final NumberBalanceRepository numberBalanceRepository;

    public BalanceNumberService(NumberBalanceRepository numberBalanceRepository) {
        this.numberBalanceRepository = numberBalanceRepository;
    }

    public void addDataFromContractToAccount(PhoneNumber phoneNumber1, Contracts contracts) {
        NumberBalance numberBalanceBuilder = NumberBalance.builder()
                .balanceAccount(0.0)
                .smsBalance(contracts.getContractSms())
                .phoneNumber(phoneNumber1)
                .balanceInternet(contracts.getContractsInternet())
                .balanceMinutes(contracts.getContractMinutes()).build();

        numberBalanceRepository.save(numberBalanceBuilder);
    }


    public boolean addDataFromPackageToAccount(PhoneNumber phoneNumber1, Package aPackage) {

        Optional<NumberBalance> byPhoneNumber = numberBalanceRepository.findByPhoneNumber(phoneNumber1);
        if (byPhoneNumber.isPresent()) {
            NumberBalance numberBalance = byPhoneNumber.get();
            numberBalanceRepository.save(sumToNumberBalance(numberBalance, aPackage));
            return true;
        }
        return false;
    }

    private NumberBalance sumToNumberBalance(NumberBalance numberBalanceFromBase, Package aPackage) {

        return NumberBalance.builder()
                .balanceAccount(0.0)
                .smsBalance(numberBalanceFromBase.getSmsBalance() + aPackage.getNumberOfSms())
                .phoneNumber(numberBalanceFromBase.getPhoneNumber())
                .balanceInternet(aPackage.getNumberOfInternet() + numberBalanceFromBase.getBalanceInternet())
                .balanceMinutes(aPackage.getNumberOfMinutes() + numberBalanceFromBase.getBalanceMinutes()).build();
    }

}
