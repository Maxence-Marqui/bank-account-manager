package bank_manager.back_end.dto;


import bank_manager.back_end.entity.Account;
import bank_manager.back_end.entity.Flag;
import bank_manager.back_end.entity.UsersAccounts;
import bank_manager.back_end.enums.EntityStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    
    @NotNull(message = "First name is mandatory")
    private String firstName;

    @NotNull(message = "First name is mandatory")
    private String lastName;

    @NotNull(message = "Email is mandatory")
    private String email;

    @NotNull(message = "Birthday is mandatory")
    private LocalDate birthday;

    @NotNull(message = "Phone number is mandatory")
    private String phoneNumber;

    @NotNull(message = "Status is mandatory")
    private EntityStatus status;

    private Flag flagId;
    private List<Long> accountIds;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String password;

    public List<Long> getAccountIds() {
        return accountIds != null ? accountIds : new ArrayList<>();
    }
}
