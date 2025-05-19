package bank_manager.back_end.repository;

import bank_manager.back_end.entity.Beneficiary;
import bank_manager.back_end.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

    @Query("""
        SELECT b FROM Beneficiary b
        WHERE b.user.id = :userId
        ORDER BY b.addedAt DESC
    """)
    List<Beneficiary> findLatestBeneficiaries(@Param("userId") Long userId, Pageable pageable);
    List<Beneficiary> findByUser(User owner);
    List<Beneficiary> findByUserId(Long id);
}

