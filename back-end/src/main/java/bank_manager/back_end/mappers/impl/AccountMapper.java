package bank_manager.back_end.mappers.impl;

import bank_manager.back_end.dto.AccountDto;
import bank_manager.back_end.entity.Account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {
    public static Account toAccount(AccountDto accountDto){
        return new Account(
                accountDto.getId(),
                accountDto.getAccountName(),
                accountDto.getMainUser(),
                accountDto.getAccountNumber(),
                accountDto.getBalance(),
                accountDto.getFlagId(),
                accountDto.getStatus(),
                accountDto.getUserAccounts()
        );
    }

    public static AccountDto toDto(Account account){
        return new AccountDto(
                account.getId(),
                account.getAccountName(),
                account.getAccountNumber(),
                account.getMainUser(),
                account.getAccountUsers(),
                account.getBalance(),
                account.getFlagId(),
                account.getStatus()
        );
    }

    public static List<AccountDto> toDtoList(List<Account> accountList){
        return accountList.stream().map(AccountMapper::toDto).collect(Collectors.toList());
    }
}

