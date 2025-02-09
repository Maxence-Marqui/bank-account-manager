package bank_manager.back_end.repository;

import bank_manager.back_end.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    public Admin findByEmail(String email);
    @Modifying
    @Query(value = "ALTER SEQUENCE admins_id_seq RESTART WITH 1", nativeQuery = true)
    void resetSequence();
}
