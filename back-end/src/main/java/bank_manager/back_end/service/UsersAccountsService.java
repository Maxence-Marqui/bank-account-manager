package bank_manager.back_end.service;

import bank_manager.back_end.entity.Account;
import bank_manager.back_end.entity.User;
import bank_manager.back_end.entity.UsersAccounts;

import java.util.List;

public interface UsersAccountsService {
    public List<Account> getUserAccounts(User user);
    public List<User> getAccountUsers(Account account);
    public void addUserToAccount(User user, Account account);
    public void removeUserFromAccount(User user, Account account);
}
