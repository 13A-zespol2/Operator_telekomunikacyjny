package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.PhoneNumber;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {


    Optional<PhoneNumber> findByNumber(String number);

    PhoneNumber findByIdPhoneNumber(Long idPhoneNumber);

}
