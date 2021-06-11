package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.ContractsPhoneNumber;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface ContractPhoneNumberRepository extends JpaRepository<ContractsPhoneNumber, Long> {

    @Query("SELECT con FROM ContractsPhoneNumber con WHERE con.phoneNumber=?1")
    List<ContractsPhoneNumber> findMonthlyCost(PhoneNumber phoneNumber);
}
