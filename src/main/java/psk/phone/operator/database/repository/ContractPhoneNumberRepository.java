package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.ContractsPhoneNumber;
import psk.phone.operator.database.entities.PhoneNumber;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Repository
public interface ContractPhoneNumberRepository extends JpaRepository<ContractsPhoneNumber, Long> {

    @Query("SELECT con FROM ContractsPhoneNumber con WHERE con.phoneNumber=?1 and con.datePurchase>?2")
    ArrayList<ContractsPhoneNumber> findMonthlyCost(PhoneNumber phoneNumber, LocalDate pDate);

}
