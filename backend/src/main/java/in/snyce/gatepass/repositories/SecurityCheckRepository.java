package in.snyce.gatepass.repositories;

import in.snyce.gatepass.model.SecurityCheck;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for accessing SecurityCheck data from the database
public interface SecurityCheckRepository extends JpaRepository<SecurityCheck, Integer> {
}
