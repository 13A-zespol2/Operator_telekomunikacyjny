package psk.phone.operator.database.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}