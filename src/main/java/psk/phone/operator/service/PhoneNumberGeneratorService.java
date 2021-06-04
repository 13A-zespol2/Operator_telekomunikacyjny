package psk.phone.operator.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psk.phone.operator.database.entities.Contracts;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.repository.ContractsRepository;
import psk.phone.operator.database.repository.PhoneNumberRepository;

import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Random;

@Service
@NoArgsConstructor
public class PhoneNumberGeneratorService {
    private final Random random = new Random();
    private PhoneNumberRepository phoneNumberRepository;
    private BalanceNumberService balanceNumberService;
    private ContractsRepository contractsRepository;

    @Autowired
    public PhoneNumberGeneratorService(PhoneNumberRepository phoneNumberRepository, BalanceNumberService balanceNumberService, ContractsRepository contractsRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
        this.balanceNumberService = balanceNumberService;
        this.contractsRepository = contractsRepository;
    }

    public PhoneNumber generatePhoneNumberForUser() {
        String phoneNumberString;
        Optional<PhoneNumber> byNumber;
        do {
            phoneNumberString = createPhoneNumber();
            byNumber = phoneNumberRepository.findByNumber(phoneNumberString);
        } while (phoneNumberString.isEmpty());
        PhoneNumber phoneNumber1 = new PhoneNumber(phoneNumberString, null);
        Optional<Contracts> contracts = contractsRepository.findById(1L);
        if (contracts.isEmpty())
            return null;
        PhoneNumber save = phoneNumberRepository.save(phoneNumber1);
        balanceNumberService.addDataFromContractToAccount(phoneNumber1, contracts.get());
        return save;
    }

    private String createPhoneNumber() {
        DecimalFormat formatPartNumber = new DecimalFormat("000");
        int partNumber;
        StringBuilder phoneNumber = new StringBuilder();

        do {
            partNumber = random.nextInt(999);
            phoneNumber.append(formatPartNumber.format(partNumber));

        } while (phoneNumber.length() != 9);

        return phoneNumber.toString();
    }

    public boolean updatePinForNumber(PhoneNumber number, String newPin) {
        Optional<PhoneNumber> byNumber = phoneNumberRepository.findByNumber(number.getNumber());

        if (byNumber.isPresent()) {
            PhoneNumber phoneNumber = byNumber.get();
            phoneNumber.setPin(newPin);
            phoneNumberRepository.save(phoneNumber);
            return true;
        }
        return false;
    }


}
