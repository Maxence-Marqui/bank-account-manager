package bank_manager.back_end.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
