package bank_manager.back_end.controller;

import bank_manager.back_end.dto.AdminDto;
import bank_manager.back_end.dto.AdminLoginDto;
import bank_manager.back_end.dto.UserDto;
import bank_manager.back_end.dto.UserLoginDto;
import bank_manager.back_end.enums.EntityType;
import bank_manager.back_end.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    @Autowired
    private final AuthService authService;


    @PostMapping("/login/users")
    public ResponseEntity<?> loginUsers(@RequestBody @Valid UserLoginDto userLoginDto) throws AuthenticationException {
        return new ResponseEntity<>(
                authService.login(
                        EntityType.USER,
                        userLoginDto.getEmail(),
                        userLoginDto.getPassword()),
                HttpStatus.OK
        );
    }

    @PostMapping("/login/admins")
    public ResponseEntity<?> loginAdmins(@RequestBody @Valid AdminLoginDto adminLoginDto) throws AuthenticationException {
        return new ResponseEntity<>(
                authService.login(
                        EntityType.ADMIN,
                        adminLoginDto.getEmail(),
                        adminLoginDto.getPassword()),
                HttpStatus.OK
        );
    }

    @PostMapping("/register/user")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto){
        return new ResponseEntity<>(authService.createUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AdminDto> createAdmin(@RequestBody @Valid AdminDto adminDto){
        return new ResponseEntity<>(authService.createAdmin(adminDto), HttpStatus.CREATED);
    }
}
