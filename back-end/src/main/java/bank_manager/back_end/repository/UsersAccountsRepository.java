package bank_manager.back_end.repository;

import bank_manager.back_end.entity.Account;
import bank_manager.back_end.entity.User;
import bank_manager.back_end.entity.UsersAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersAccountsRepository extends JpaRepository<UsersAccounts, Long> {
    UsersAccounts findByUserAndAccountAndLeftAtIsNull(User user, Account account);
    List<UsersAccounts> findByAccount(Account account);
    List<UsersAccounts> findByAccountId(Long accountId);
    List<UsersAccounts> findByUser(User user);
    List<UsersAccounts> findByUserId(Long userId);

    @Modifying
    @Query(value = "ALTER SEQUENCE users_accounts_id_seq RESTART WITH 1", nativeQuery = true)
    void resetSequence();
}
