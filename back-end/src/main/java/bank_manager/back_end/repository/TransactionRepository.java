package bank_manager.back_end.repository;

import bank_manager.back_end.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Modifying
    @Query(value = "ALTER SEQUENCE transactions_id_seq RESTART WITH 1", nativeQuery = true)
    void resetSequence();
}
