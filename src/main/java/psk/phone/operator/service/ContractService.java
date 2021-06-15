package psk.phone.operator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psk.phone.operator.database.entities.Contracts;
import psk.phone.operator.database.entities.ContractsPhoneNumber;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.repository.ContractPhoneNumberRepository;
import psk.phone.operator.database.repository.ContractsRepository;

import java.time.LocalDate;
import java.util.Optional;


/**
 * Klasa zarządzająca przypisywaniem pakietu startowego do nowo utworzonego numeru.
 */
@Service
public class ContractService {
    private final ContractPhoneNumberRepository contractPhoneNumberRepository;
    private final ContractsRepository contractsRepository;

    @Autowired
    public ContractService(ContractPhoneNumberRepository contractPhoneNumberRepository, ContractsRepository contractsRepository) {
        this.contractPhoneNumberRepository = contractPhoneNumberRepository;
        this.contractsRepository = contractsRepository;
    }


    /**
     * Metoda odpowiedzialna za przypisanie pakietu startowego do danego numeru.
     * @param phoneNumber
     * @param idContract
     * @return
     */
    public Optional<ContractsPhoneNumber> assignContractToPhoneNUmber(PhoneNumber phoneNumber, long idContract) {
        Optional<Contracts> contractsOptional = contractsRepository.findById(idContract);
        if (contractsOptional.isPresent()) {
            Contracts contracts1 = contractsOptional.get();
            ContractsPhoneNumber save = contractPhoneNumberRepository.save(new ContractsPhoneNumber(contracts1, phoneNumber, LocalDate.now()));
            return Optional.of(save);
        }
        return Optional.empty();
    }
}
