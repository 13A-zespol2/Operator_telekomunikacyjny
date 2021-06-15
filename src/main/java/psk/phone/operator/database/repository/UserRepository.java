package psk.phone.operator.database.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import psk.phone.operator.database.entities.User;

import java.util.List;
import java.util.Optional;


/**
 * Interfejs odpowiadający za dostęp do tabeli ,,user" w bazie danych.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  @Override
  List<User> findAll();

  User findByIdUser (Long id_user);
}