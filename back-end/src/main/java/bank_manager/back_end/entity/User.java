package bank_manager.back_end.entity;

import bank_manager.back_end.enums.EntityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "flag_id", referencedColumnName = "id")
    private Flag flagId = null;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private EntityStatus status;

    @OneToMany(mappedBy = "user")
    private List<UsersAccounts> userAccounts = new ArrayList<>();

}
