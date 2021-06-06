package psk.phone.operator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psk.phone.operator.database.entities.Contract;
import psk.phone.operator.database.entities.ContractsPhoneNumber;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.repository.ContractPhoneNumberRepository;
import psk.phone.operator.database.repository.ContractsRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ContractsService {
    private final ContractPhoneNumberRepository contractPhoneNumberRepository;
    private final ContractsRepository contractsRepository;

    @Autowired
    public ContractsService(ContractPhoneNumberRepository contractPhoneNumberRepository, ContractsRepository contractsRepository) {
        this.contractPhoneNumberRepository = contractPhoneNumberRepository;
        this.contractsRepository = contractsRepository;
    }

    public Optional<ContractsPhoneNumber> assignContractToPhoneNUmber(PhoneNumber phoneNumber, long idContract) {
        Optional<Contract> contractsOptional = contractsRepository.findById(idContract);
        if (contractsOptional.isPresent()) {
            Contract contract1 = contractsOptional.get();
            ContractsPhoneNumber save = contractPhoneNumberRepository.save(new ContractsPhoneNumber(contract1, phoneNumber, LocalDate.now()));
            return Optional.of(save);
        }
        return Optional.empty();
    }


}
