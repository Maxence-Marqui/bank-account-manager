package bank_manager.back_end.mappers.impl;

import bank_manager.back_end.dto.AccountDto;
import bank_manager.back_end.entity.Account;
import bank_manager.back_end.entity.User;
import bank_manager.back_end.entity.UsersAccounts;
import bank_manager.back_end.repository.UserRepository;
import bank_manager.back_end.repository.UsersAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {

    public static Account toAccount(AccountDto accountDto, User mainUser, List<UsersAccounts> usersAccounts){

        return new Account(
                accountDto.getId(),
                accountDto.getAccountName(),
                mainUser,
                accountDto.getAccountNumber(),
                accountDto.getBalance(),
                accountDto.getFlagId(),
                accountDto.getStatus(),
                usersAccounts
        );
    }

    public static AccountDto toDto(Account account){

        List<Long> userIds = account.getAccountUsers().stream()
                .map(usersAccounts -> usersAccounts.getUser().getId())
                .toList();

        return new AccountDto(
                account.getId(),
                account.getAccountName(),
                account.getAccountNumber(),
                account.getMainUser().getId(),
                userIds,
                account.getBalance(),
                account.getFlagId(),
                account.getStatus()
        );
    }

    public static List<AccountDto> toDtoList(List<Account> accountList){
        return accountList.stream().map(AccountMapper::toDto).collect(Collectors.toList());
    }
}

