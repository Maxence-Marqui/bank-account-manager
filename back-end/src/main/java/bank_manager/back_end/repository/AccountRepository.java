package bank_manager.back_end.repository;


import bank_manager.back_end.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findById(Account savedAccount);

    @Modifying
    @Query(value = "ALTER SEQUENCE accounts_id_seq RESTART WITH 1", nativeQuery = true)
    void resetSequence();

    Optional<Account> findByAccountNumber(String accountNumber);
}
