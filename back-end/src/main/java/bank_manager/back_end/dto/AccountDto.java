package bank_manager.back_end.dto;

import bank_manager.back_end.entity.Flag;
import bank_manager.back_end.entity.User;
import bank_manager.back_end.entity.UsersAccounts;
import bank_manager.back_end.enums.EntityStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    private Long id;

    @NotNull(message = "Account name must not be null")
    private String accountName;
    private String accountNumber;
    private User mainUser;
    private List<UsersAccounts> userAccounts;
    private Double balance;
    private Flag flagId;

    @NotNull(message = "Status must not be null")
    private EntityStatus status;
}
