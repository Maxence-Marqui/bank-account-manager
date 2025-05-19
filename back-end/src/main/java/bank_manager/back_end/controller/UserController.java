package bank_manager.back_end.controller;

import bank_manager.back_end.dto.AccountDto;
import bank_manager.back_end.dto.BeneficiaryDto;
import bank_manager.back_end.dto.UserDto;
import bank_manager.back_end.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final BeneficiaryService beneficiaryService;
    private final TransactionService transactionService;
    private final UsersAccountsService usersAccountsService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/ids")
    public ResponseEntity<List<UserDto>> getUsersById(@RequestParam List<Long> ids) {
        return new ResponseEntity<>(userService.getUserListById(ids), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getUserAccounts(@RequestAttribute("entityId") Long id) {
        return new ResponseEntity<>(usersAccountsService.getUserAccounts(id), HttpStatus.OK);
    }


    @PostMapping("/beneficiaries")
    public ResponseEntity<List<BeneficiaryDto>> addUserBeneficiary(
            @RequestBody BeneficiaryDto beneficiaryDto,
            @RequestAttribute("entityId") Long entityId
    ) {
        return new ResponseEntity<>(beneficiaryService.addUserBeneficiary(entityId, beneficiaryDto), HttpStatus.CREATED);
    }

    @GetMapping("/beneficiaries/{beneficiaryId}")
    public ResponseEntity<BeneficiaryDto> getUserBeneficiary(@PathVariable Long beneficiaryId){
        return new ResponseEntity<>(beneficiaryService.getBeneficiary(beneficiaryId), HttpStatus.OK);
    }

    @GetMapping("/beneficiaries")
    public ResponseEntity<List<BeneficiaryDto>> getUserBeneficiaries(@RequestAttribute("entityId") Long entityId) {
        return new ResponseEntity<>(beneficiaryService.getUserBeneficiaries(entityId), HttpStatus.OK);
    }

    @PatchMapping("/beneficiaries/{beneficiaryId}")
    public ResponseEntity<BeneficiaryDto> editBeneficiary(@PathVariable Long beneficiaryId, @RequestBody BeneficiaryDto beneficiaryDto){
        return new ResponseEntity<>(beneficiaryService.editBeneficiary(beneficiaryId, beneficiaryDto), HttpStatus.OK);
    }

    @DeleteMapping("/beneficiaries/{beneficiaryId}")
    public ResponseEntity<List<BeneficiaryDto>> removeUserBeneficiary(@PathVariable Long beneficiaryId) {
        beneficiaryService.removeUserBeneficiary(beneficiaryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dashboard")
    public ResponseEntity<HashMap<String, Object>> getUserDashboard(@RequestAttribute("entityId") Long entityId){
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("beneficiaries", beneficiaryService.getLatestBeneficiaries(entityId));
        responseData.put("accounts", usersAccountsService.getUserLatestActiveAccounts(entityId));
        responseData.put("transactions", transactionService.getLatestTransactions(entityId));

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
