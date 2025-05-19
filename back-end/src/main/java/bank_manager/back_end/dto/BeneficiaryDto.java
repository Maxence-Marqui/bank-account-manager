package bank_manager.back_end.dto;

import bank_manager.back_end.entity.Account;
import bank_manager.back_end.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeneficiaryDto {
    private Long id;

    @NotBlank
    private String accountName;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    private String accountNumber;

    private Account internalAccount;

    private LocalDate addedAt;

}
