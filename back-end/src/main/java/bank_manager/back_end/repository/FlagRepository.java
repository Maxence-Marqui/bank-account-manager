package bank_manager.back_end.repository;

import bank_manager.back_end.entity.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface FlagRepository extends JpaRepository<Flag, Long> {
    @Modifying
    @Query(value = "ALTER SEQUENCE flags_id_seq RESTART WITH 1", nativeQuery = true)
    void resetSequence();
}
