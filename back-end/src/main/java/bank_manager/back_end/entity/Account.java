package bank_manager.back_end.entity;

import bank_manager.back_end.enums.EntityStatus;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts", uniqueConstraints = @UniqueConstraint(columnNames = "account_number"))
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_name")
    private String accountName;

    @ManyToOne
    @JoinColumn(name = "main_user_id", nullable = false, referencedColumnName = "id")
    private User mainUser;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "balance", nullable = false, columnDefinition = "double precision default 0")
    private double balance = 0;

    @OneToOne
    @JoinColumn(name = "flag_id", referencedColumnName = "id")
    private Flag flagId = null;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private EntityStatus status;

    @OneToMany(mappedBy = "account")
    private List<UsersAccounts> accountUsers = new ArrayList<>();
}
