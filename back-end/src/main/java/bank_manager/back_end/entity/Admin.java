package bank_manager.back_end.entity;

import bank_manager.back_end.enums.AdminTier;
import bank_manager.back_end.enums.EntityStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admins", uniqueConstraints = @UniqueConstraint( columnNames = "email"))
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Admin tier is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "admin_tier", nullable = false)
    private AdminTier adminTier;

    @NotBlank(message = "Email is mandatory")
    @Size(min = 5, message = "The email must be 5 characters at minimum")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is mendatory")
    @Size(min = 5, message = "The password must be 5 characters at minimum")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull(message = "Entity Status is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private EntityStatus status;
}
