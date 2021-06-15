package psk.phone.operator.database.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Klasa odpowiadająca za tabelę ,,User" (przechowuje dane użytkownika, np. e-mail, hasło).
 */
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private String password;

    public User(Long idUser, String name, String surname) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
    }

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
