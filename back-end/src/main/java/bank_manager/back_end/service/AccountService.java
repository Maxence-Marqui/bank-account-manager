package bank_manager.back_end.service;

import bank_manager.back_end.dto.AccountDto;
import bank_manager.back_end.dto.UserDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto updateAccount(Long id, AccountDto accountDto);
    AccountDto deleteAccount(Long id);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAllAccount();
    List<AccountDto> getAccountListById(List<Long> idList);
    List<UserDto> getAccountUsers(Long id);
    List<UserDto> addUserToAccount(Long accountId, Long userId);
    List<UserDto> removeUserFromAccount(Long accountId, Long userId);
    AccountDto changeMainUser(Long accountId, Long userId);
}
