package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.entities.UserPhoneNumber;

import java.util.List;

@Repository
public interface UserPhoneNumberRepository extends JpaRepository<UserPhoneNumber, Long> {

    List<UserPhoneNumber> findByUser(User user);

}
