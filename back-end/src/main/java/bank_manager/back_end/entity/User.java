package bank_manager.back_end.entity;

import bank_manager.back_end.enums.EntityStatus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "FirstName is mandatory")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "LastName is mandatory")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is mendatory")
    @Size(min = 5, message = "The password must be 5 characters at minimum")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull(message = "Birthday is mandatory")
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @NotBlank(message = "Phone Number is mandatory")
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

    public User(Long id){
        this.id = id;
    }

    public List<UsersAccounts> getUserAccounts() {
        return userAccounts.stream()
                .filter(ua -> ua.getLeftAt() == null)
                .toList();
        //return usersAccountsList.isEmpty() ? userAccounts : new ArrayList<>();
    }
}
