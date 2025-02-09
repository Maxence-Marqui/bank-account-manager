package bank_manager.back_end.controller;

import bank_manager.back_end.dto.AccountDto;
import bank_manager.back_end.dto.UserDto;
import bank_manager.back_end.entity.User;
import bank_manager.back_end.service.AccountService;
import bank_manager.back_end.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllAccount(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
    }

    @GetMapping("/ids")
    public ResponseEntity<List<AccountDto>> getAccountsByIds(@RequestBody List<Long> ids){
        return new ResponseEntity<>(accountService.getAccountListById(ids), HttpStatus.OK);
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<UserDto>> getAccountUsers(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getAccountUsers(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @PostMapping("{accountId}/users/{userId}")
    public ResponseEntity<List<UserDto>> addUserToAccount(@PathVariable Long accountId, @PathVariable Long userId){
        return new ResponseEntity<>(accountService.addUserToAccount(accountId, userId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.updateAccount(id, accountDto), HttpStatus.OK);
    }

    @PatchMapping("{accountId}/main-user/{userId}")
    public ResponseEntity<AccountDto> changeMainUser(@PathVariable Long accountId, @PathVariable Long userId){
        return new ResponseEntity<>(accountService.changeMainUser(accountId, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable Long id){
        return new ResponseEntity<>(accountService.deleteAccount(id), HttpStatus.OK);
    }

    @DeleteMapping("{accountId}/users/{userId}")
    public ResponseEntity<List<UserDto>> removeUserFromAccount(@PathVariable Long accountId, @PathVariable Long userId){
        return new ResponseEntity<>(accountService.removeUserFromAccount(accountId, userId), HttpStatus.OK);
    }

}
