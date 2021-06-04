package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.PhoneNumber;
import psk.phone.operator.database.entities.User;
import psk.phone.operator.database.entities.UserPhoneNumber;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserPhoneNumberRepository extends JpaRepository<UserPhoneNumber, Long> {

    @Query("SELECT upn.phoneNumber FROM UserPhoneNumber upn WHERE upn.user=?1")
    ArrayList<PhoneNumber> findByIdUserPhoneNumber(User user);

    List<UserPhoneNumber> findByUser(User user);
}
