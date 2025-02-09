package bank_manager.back_end.service.impl;

import bank_manager.back_end.dto.AccountDto;
import bank_manager.back_end.dto.UserDto;
import bank_manager.back_end.entity.Account;
import bank_manager.back_end.entity.User;
import bank_manager.back_end.entity.UsersAccounts;
import bank_manager.back_end.enums.EntityStatus;
import bank_manager.back_end.mappers.impl.AccountMapper;
import bank_manager.back_end.mappers.impl.UserMapper;
import bank_manager.back_end.repository.AccountRepository;
import bank_manager.back_end.repository.UserRepository;
import bank_manager.back_end.repository.UsersAccountsRepository;
import bank_manager.back_end.service.AccountService;
import bank_manager.back_end.service.UsersAccountsService;
import bank_manager.back_end.utils.RandomUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UsersAccountsService usersAccountsService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UsersAccountsRepository usersAccountsRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        User mainUser = userRepository.findById(accountDto.getMainUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = AccountMapper.toAccount(accountDto, mainUser, new ArrayList<>());
        account.setAccountNumber(RandomUtils.generateUniqueAccountNumber());

        Account savedAccount = accountRepository.save(account);

        UsersAccounts usersAccounts = new UsersAccounts();
        usersAccounts.setAccount(savedAccount);
        usersAccounts.setUser(account.getMainUser());
        usersAccounts.setJoinedAt(LocalDate.now());
        usersAccountsRepository.save(usersAccounts);

        savedAccount.getAccountUsers().add(usersAccounts);

        return AccountMapper.toDto(savedAccount);
    }

    @Override
    public AccountDto updateAccount(Long id, AccountDto accountDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));

        if (accountDto.getAccountName() != null){
            account.setAccountName(accountDto.getAccountName());
        }

        if (accountDto.getStatus() != null){
            account.setStatus(accountDto.getStatus());
        }

        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toDto(savedAccount);
    }

    @Override
    public AccountDto deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
        account.setStatus(EntityStatus.ARCHIVED);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        return AccountMapper.toDtoList(accountRepository.findAll());
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
        return AccountMapper.toDto(account);
    }

    @Override
    public List<AccountDto> getAccountListById(List<Long> idList) {
        List<Account> accountList = accountRepository.findAllById(idList);
        return AccountMapper.toDtoList(accountList);
    }

    @Override
    public List<UserDto> addUserToAccount(Long accountId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountId));

        usersAccountsService.addUserToAccount(user, account);
        List<User> userList = usersAccountsService.getAccountUsers(account);
        return UserMapper.toDtoList(userList);
    }

    @Override
    public List<UserDto> removeUserFromAccount(Long accountId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountId));

        usersAccountsService.removeUserFromAccount(user, account);
        List<User> userList = usersAccountsService.getAccountUsers(account);
        return UserMapper.toDtoList(userList);
    }

    @Override
    public AccountDto changeMainUser(Long accountId, Long userId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        account.setMainUser(user);
        Account savecAccount = accountRepository.save(account);
        return AccountMapper.toDto(savecAccount);
    }

    @Override
    public List<UserDto> getAccountUsers(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountId));

        List<User> userList = usersAccountsService.getAccountUsers(account);
        return UserMapper.toDtoList(userList);
    }
}
