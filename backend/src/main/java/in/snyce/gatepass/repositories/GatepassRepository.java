package in.snyce.gatepass.repositories;

import in.snyce.gatepass.model.Gatepass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

@Repository
public interface GatepassRepository extends JpaRepository<Gatepass, Long> {

    Page<Gatepass> findByStatus(String status, Pageable pageable);

    Page<Gatepass> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    Page<Gatepass> findByStatusAndCreatedAtBetween(String status, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    @Query("SELECT COUNT(g) FROM Gatepass g WHERE FUNCTION('YEAR', g.createdAt) = :year")
    long countByYear(@Param("year") int year);

    Page<Gatepass> findByVendorNameContainingIgnoreCase(String vendorName, Pageable pageable);


    @Query("SELECT g FROM Gatepass g " +
            "WHERE (:status IS NULL OR g.status = :status) " +
            "AND (:vendorName IS NULL OR LOWER(g.vendorName) LIKE LOWER(CONCAT('%', :vendorName, '%'))) " +
            "AND (:startDate IS NULL OR g.createdAt >= :startDate) " +
            "AND (:endDate IS NULL OR g.createdAt <= :endDate)")
    Page<Gatepass> searchGatepasses(
            @Param("status") String status,
            @Param("vendorName") String vendorName,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
}
