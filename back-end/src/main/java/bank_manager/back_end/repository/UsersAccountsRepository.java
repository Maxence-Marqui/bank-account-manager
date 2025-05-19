package bank_manager.back_end.repository;

import bank_manager.back_end.entity.Account;
import bank_manager.back_end.entity.User;
import bank_manager.back_end.entity.UsersAccounts;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersAccountsRepository extends JpaRepository<UsersAccounts, Long> {
    UsersAccounts findByUserAndAccountAndLeftAtIsNull(User user, Account account);
    List<UsersAccounts> findByAccount(Account account);
    List<UsersAccounts> findByAccountId(Long accountId);
    List<UsersAccounts> findByUserId(Long userId);

    @Modifying
    @Query(value = "ALTER SEQUENCE users_accounts_id_seq RESTART WITH 1", nativeQuery = true)
    void resetSequence();

    @Query("""
    SELECT a FROM Account a
    JOIN UsersAccounts ua ON ua.account.id = a.id
    WHERE ua.user.id = :userId
    AND ua.leftAt IS NULL
    AND a.status NOT LIKE "ARCHIVED"
    """)
    List<Account> findActiveUserAccounts(@Param("userId") Long userId);


    @Query("""
    SELECT a FROM Account a
    JOIN UsersAccounts ua ON ua.account.id = a.id
    WHERE ua.user.id = :userId
    AND ua.leftAt IS NULL
    AND a.status NOT LIKE "ARCHIVED"
    """)
    List<Account> findLastestActiveUserAccounts(@Param("userId") Long userId, Pageable pageable);

}
