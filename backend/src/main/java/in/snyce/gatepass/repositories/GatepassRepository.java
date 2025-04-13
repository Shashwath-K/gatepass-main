package in.snyce.gatepass.repositories;

import in.snyce.gatepass.model.Gatepass;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for accessing Gatepass data from the database
public interface GatepassRepository extends JpaRepository<Gatepass, Integer> {
}
