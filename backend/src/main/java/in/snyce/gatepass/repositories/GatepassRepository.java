package in.snyce.gatepass.repositories;

import in.snyce.gatepass.model.Gatepass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GatepassRepository extends JpaRepository<Gatepass, Long> {
    List<Gatepass> findByStatus(String status);
    List<Gatepass> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Gatepass> findByStatusAndCreatedAtBetween(String status, LocalDateTime startDate, LocalDateTime endDate);
}
