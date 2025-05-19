package bank_manager.back_end.dto;

import bank_manager.back_end.entity.Flag;
import bank_manager.back_end.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {

    private Long id;

    @NotNull
    private String fromAccountNumber;

    @NotBlank
    private String toAccountNumber;

    @NotNull
    private double amount;

    private String note;
    private Flag flag;

    @NotNull
    private LocalDate createdAt;

    private TransactionType type;

}
