package psk.phone.operator.service;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.parameters.P;
import psk.phone.operator.database.entities.Contracts;
import psk.phone.operator.database.entities.ContractsPhoneNumber;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.repository.ContractPhoneNumberRepository;
import psk.phone.operator.database.repository.ContractsRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ContractServiceTest {

    private ContractsRepository contractsRepository;
    private ContractPhoneNumberRepository contractPhoneNumberRepository;
    private ContractService contractService;


    @BeforeEach
    public void setup(){
        contractsRepository = Mockito.mock(ContractsRepository.class);
        contractPhoneNumberRepository = Mockito.mock(ContractPhoneNumberRepository.class);
        this.contractService = new ContractService(contractPhoneNumberRepository, contractsRepository);
    }

    /*@Test
    public void ifContractAssignedToPhoneNumber(){
        //given

        PhoneNumber phoneNumber = new PhoneNumber("123123123", null);
        Contracts contracts = new Contracts(1L, "Test", 10, 10, 10, 20);
        Mockito.when(contractsRepository.findById(contracts.getIdContract())).thenReturn(Optional.of(contracts));

        //when
        contractService.assignContractToPhoneNUmber(phoneNumber, contracts.getIdContract());

        //then
        ContractsPhoneNumber expectedContractPhone = new ContractsPhoneNumber(1L, contracts, phoneNumber, LocalDate.now());
        Mockito.verify(contractPhoneNumberRepository, Mockito.times(1)).save(Mockito.eq(expectedContractPhone));
    }*/
}