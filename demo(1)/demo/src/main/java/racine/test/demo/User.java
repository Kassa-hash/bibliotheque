package racine.test.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // Constructeurs, getters et setters
    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}