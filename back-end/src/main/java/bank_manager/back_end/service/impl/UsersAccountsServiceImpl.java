package bank_manager.back_end.service.impl;

import bank_manager.back_end.entity.Account;
import bank_manager.back_end.entity.User;
import bank_manager.back_end.entity.UsersAccounts;
import bank_manager.back_end.repository.AccountRepository;
import bank_manager.back_end.repository.UserRepository;
import bank_manager.back_end.repository.UsersAccountsRepository;
import bank_manager.back_end.service.UsersAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersAccountsServiceImpl implements UsersAccountsService {

    @Autowired
    private UsersAccountsRepository usersAccountsRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Account> getUserAccounts(User user) {
        List<UsersAccounts> usersAccounts = usersAccountsRepository.findByUser(user);

        return usersAccounts.stream()
                .map(UsersAccounts::getAccount)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAccountUsers(Account account) {
        List<UsersAccounts> usersAccounts = usersAccountsRepository.findByAccount(account);

        return usersAccounts.stream()
                .map(UsersAccounts::getUser)
                .collect(Collectors.toList());
    }

    @Override
    public void addUserToAccount(User user, Account account) {
        UsersAccounts existingRelation = usersAccountsRepository.findByUserAndAccountAndLeftAtIsNull(user, account);
        if (existingRelation != null) {
            throw new RuntimeException("User is already added to this account");
        }

        UsersAccounts usersAccounts = new UsersAccounts(null, account, user, LocalDate.now(), null);
        usersAccountsRepository.save(usersAccounts);
    }

    @Override
    public void removeUserFromAccount(User user, Account account) {

        UsersAccounts usersAccounts = usersAccountsRepository.findByUserAndAccountAndLeftAtIsNull(user, account);
        if (usersAccounts == null) {
            throw new RuntimeException("User is not currently associated with this account");
        }

        usersAccounts.setLeftAt(LocalDate.now());
        usersAccountsRepository.save(usersAccounts);
    }
}

