package psk.phone.operator.service;

import org.springframework.stereotype.Service;
import psk.phone.operator.database.entities.Contract;
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

    public void addDataFromContractToAccount(PhoneNumber phoneNumber1, Contract contract) {
        NumberBalance numberBalanceBuilder = NumberBalance.builder()
                .balanceAccount(0.0)
                .smsBalance(contract.getContractSms())
                .phoneNumber(phoneNumber1)
                .balanceInternet(contract.getContractsInternet())
                .balanceMinutes(contract.getContractMinutes()).build();

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
