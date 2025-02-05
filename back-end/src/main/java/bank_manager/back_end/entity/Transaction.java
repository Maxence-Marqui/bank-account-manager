package bank_manager.back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id")
    private Account account;

    @Column(name = "receiving_account_number", nullable = false)
    private String receivingAccountNumber;

    @Column(name = "sending_account_number", nullable = false)
    private String sendingAccountNumber;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name= "note")
    private String note;

    @OneToOne
    @JoinColumn(name = "flag_id", referencedColumnName = "id")
    private Flag flagId = null;
}
