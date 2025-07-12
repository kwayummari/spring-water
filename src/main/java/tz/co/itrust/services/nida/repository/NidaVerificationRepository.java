package tz.co.itrust.services.nida.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tz.co.itrust.services.nida.entity.NidaVerificationEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for NIDA verification entities
 */
@Repository
public interface NidaVerificationRepository extends JpaRepository<NidaVerificationEntity, UUID> {

    /**
     * Find verification by NIN
     */
    Optional<NidaVerificationEntity> findByNin(String nin);

    /**
     * Find latest verification for a NIN
     */
    @Query("SELECT v FROM NidaVerificationEntity v WHERE v.nin = :nin ORDER BY v.verificationDate DESC")
    Optional<NidaVerificationEntity> findLatestByNin(@Param("nin") String nin);

    /**
     * Find verifications by status
     */
    List<NidaVerificationEntity> findByVerificationStatus(
        NidaVerificationEntity.VerificationStatus status);

    /**
     * Find verifications by date range
     */
    @Query("SELECT v FROM NidaVerificationEntity v WHERE v.verificationDate BETWEEN :startDate AND :endDate")
    Page<NidaVerificationEntity> findByVerificationDateBetween(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        Pageable pageable);

    /**
     * Find verifications by type
     */
    List<NidaVerificationEntity> findByVerificationType(String verificationType);

    /**
     * Count successful verifications
     */
    @Query("SELECT COUNT(v) FROM NidaVerificationEntity v WHERE v.verificationStatus = :status")
    long countByVerificationStatus(@Param("status") NidaVerificationEntity.VerificationStatus status);

    /**
     * Find recent verifications
     */
    @Query("SELECT v FROM NidaVerificationEntity v WHERE v.verificationDate >= :since ORDER BY v.verificationDate DESC")
    List<NidaVerificationEntity> findRecentVerifications(@Param("since") LocalDateTime since);

    /**
     * Check if NIN exists in our records
     */
    boolean existsByNin(String nin);

    /**
     * Find verifications by NIN pattern (for reporting)
     */
    @Query("SELECT v FROM NidaVerificationEntity v WHERE v.nin LIKE :pattern")
    List<NidaVerificationEntity> findByNinPattern(@Param("pattern") String pattern);
}