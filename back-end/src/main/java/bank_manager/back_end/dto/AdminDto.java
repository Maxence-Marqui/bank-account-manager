package bank_manager.back_end.dto;

import bank_manager.back_end.enums.AdminTier;
import bank_manager.back_end.enums.EntityStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDto {


    private Long id;

    @NotNull(message = "Admin tier is mandatory")
    private AdminTier adminTier;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotNull(message = "Status is mendatory")
    private EntityStatus status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String password;
}
