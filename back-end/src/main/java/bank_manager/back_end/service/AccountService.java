package bank_manager.back_end.service;

import bank_manager.back_end.dto.AccountDto;
import bank_manager.back_end.dto.UserDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> createAccount(Long userId, AccountDto accountDto);
    List<AccountDto> updateAccount(Long userId, Long accountId, AccountDto accountDto);
    List<AccountDto> deleteAccount(Long userId, Long accountId);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAllAccount();
    List<AccountDto> getAccountListById(List<Long> idList);
    List<UserDto> getAccountUsers(Long id);
    List<UserDto> addUserToAccount(Long accountId, Long userId);
    List<UserDto> removeUserFromAccount(Long accountId, Long userId);
    AccountDto changeMainUser(Long accountId, Long userId);
}
