package bank_manager.back_end.repository;

import bank_manager.back_end.entity.UsersActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UsersActionsRepository extends JpaRepository<UsersActions, Long> {
    @Modifying
    @Query(value = "ALTER SEQUENCE users_actions_id_seq RESTART WITH 1", nativeQuery = true)
    void resetSequence();
}
