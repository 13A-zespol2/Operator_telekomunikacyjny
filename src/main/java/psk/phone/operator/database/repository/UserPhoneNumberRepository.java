package psk.phone.operator.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhoneNumberRepository extends JpaRepository<UserPhoneNumberRepository, Long> {
}
