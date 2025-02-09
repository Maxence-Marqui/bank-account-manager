package bank_manager.back_end.controller;

import bank_manager.back_end.dto.AdminLoginDto;
import bank_manager.back_end.dto.UserDto;
import bank_manager.back_end.dto.UserLoginDto;
import bank_manager.back_end.enums.EntityType;
import bank_manager.back_end.service.AuthService;
import bank_manager.back_end.service.UserService;
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

    @Autowired
    private final UserService userService;

    @PostMapping("/login/users")
    public String loginUsers(@RequestBody @Valid UserLoginDto userLoginDto) throws AuthenticationException {
        return authService.login(EntityType.USER,userLoginDto.getEmail(), userLoginDto.getPassword());
    }

    @PostMapping("/login/admins")
    public String loginAdmins(@RequestBody @Valid AdminLoginDto adminLoginDto) throws AuthenticationException {
        return authService.login(EntityType.ADMIN, adminLoginDto.getEmail(), adminLoginDto.getPassword());
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
}
