package bank_manager.back_end.repository;

import bank_manager.back_end.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Modifying
    @Query(value = "ALTER SEQUENCE users_id_seq RESTART WITH 1", nativeQuery = true)
    void resetSequence();
}
