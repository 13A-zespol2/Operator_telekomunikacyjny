package psk.phone.operator.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.repository.PhoneNumberRepository;

import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Random;
//TODO DO TESTOW + OBSLUGA ZAPYTAN
@Service
@NoArgsConstructor
public class PhoneNumberGeneratorService {
    private final Random random = new Random();
    private PhoneNumberRepository phoneNumberRepository;


    @Autowired
    public PhoneNumberGeneratorService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public PhoneNumber generatePhoneNumberForUser() {
        String phoneNumber;
        Optional<PhoneNumber> byNumber;
        do {
            phoneNumber = createPhoneNumber();
            byNumber = phoneNumberRepository.findByNumber(phoneNumber);
        } while (phoneNumber.isEmpty());
        return phoneNumberRepository.save(new PhoneNumber(phoneNumber, null));
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

    public boolean updatePinForNumber(String number, String newPin) {
        Optional<PhoneNumber> byNumber = phoneNumberRepository.findByNumber(number);

        if (byNumber.isPresent()) {
            PhoneNumber phoneNumber = byNumber.get();
            phoneNumber.setPin(newPin);
            phoneNumberRepository.save(phoneNumber);
            return true;
        }
        return false;
    }


}
