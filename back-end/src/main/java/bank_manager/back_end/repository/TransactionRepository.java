package bank_manager.back_end.repository;

import bank_manager.back_end.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Modifying
    @Query(value = "ALTER SEQUENCE transactions_id_seq RESTART WITH 1", nativeQuery = true)
    void resetSequence();

    @Query("""
            SELECT t FROM Transaction t
            WHERE t.fromAccountNumber IN (
                SELECT a.accountName FROM Account a
                WHERE a.id IN (SELECT ua.account.id FROM UsersAccounts ua WHERE ua.user.id = :userId)
            ) 
            OR t.toAccountNumber IN (
                SELECT a.accountNumber FROM Account a 
                WHERE a.id IN (SELECT ua.account.id FROM UsersAccounts ua WHERE ua.user.id = :userId)
            )
            """)
    List<Transaction> findByUser(@Param("userId") Long userId);


    @Query("""
            SELECT t FROM Transaction t
            WHERE t.fromAccountNumber IN (
                SELECT a.accountName FROM Account a
                WHERE a.id IN (SELECT ua.account.id FROM UsersAccounts ua WHERE ua.user.id = :userId)
            ) 
            OR t.toAccountNumber IN (
                SELECT a.accountNumber FROM Account a 
                WHERE a.id IN (SELECT ua.account.id FROM UsersAccounts ua WHERE ua.user.id = :userId)
            )
            ORDER BY t.createdAt DESC
            """)
    Page<Transaction> findUserLatestTransactions(@Param("userId") Long userId, Pageable pageable);


    @Query("""
            SELECT t FROM Transaction t
            WHERE t.fromAccountNumber IN (
                SELECT a.accountName FROM Account a WHERE a.id = :accountId
            )
            OR t.toAccountNumber IN (
                SELECT a.accountNumber FROM Account a WHERE a.id = :accountId
            )
            """)
    List<Transaction> findBySimilarAccountName(@Param("accountId") Long accountId);
}
