package in.snyce.gatepass.services.impl;

import in.snyce.gatepass.model.Gatepass;
import in.snyce.gatepass.repositories.GatepassRepository;
import in.snyce.gatepass.services.GatepassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of GatepassService for managing gatepass operations.
 * Provides business logic and interacts with the GatepassRepository.
 */
@Service
public class GatepassServiceImpl implements GatepassService {

    @Autowired
    private GatepassRepository gatepassRepository;

    /**
     * Creates and stores a new gatepass entry in the database.
     * Automatically sets the created time and default status as "Pending".
     *
     * @param gatepass the Gatepass object to create
     * @return the saved Gatepass entity
     */
    @Override
    public Gatepass createGatepass(Gatepass gatepass) {
        gatepass.setCreatedAt(LocalDateTime.now());
        gatepass.setStatus("Pending");
        return gatepassRepository.save(gatepass);
    }

    /**
     * Retrieves all gatepasses from the database.
     *
     * @return list of all gatepasses
     */
    @Override
    public List<Gatepass> getAllGatepasses() {
        return gatepassRepository.findAll();
    }

    /**
     * Retrieves a specific gatepass by its ID.
     *
     * @param id the ID of the gatepass
     * @return the matching Gatepass or null if not found
     */
    @Override
    public Gatepass getGatepassById(Long id) {
        Optional<Gatepass> gatepass = gatepassRepository.findById((long) id.intValue());
        return gatepass.orElse(null);
    }

    /**
     * Updates the status of a specific gatepass.
     * Also updates the modification timestamp.
     *
     * @param id     the ID of the gatepass to update
     * @param status the new status value
     * @return the updated Gatepass or null if not found
     */
    @Override
    public Gatepass updateStatus(Long id, String status) {
        Gatepass gatepass = getGatepassById(id);
        if (gatepass != null) {
            gatepass.setStatus(status);
            gatepass.setUpdatedAt(LocalDateTime.now());
            return gatepassRepository.save(gatepass);
        }
        return null;
    }

    /**
     * Retrieves gatepasses based on status and/or date range filters.
     * If no filters are provided, returns all gatepasses.
     *
     * @param status    optional filter by status (e.g., "active", "pending")
     * @param startDate optional filter for createdAt start time
     * @param endDate   optional filter for createdAt end time
     * @return filtered list of Gatepass records
     */
    @Override
    public List<Gatepass> getGatepasses(String status, LocalDateTime startDate, LocalDateTime endDate) {
        if (status != null && startDate != null && endDate != null) {
            return gatepassRepository.findByStatusAndCreatedAtBetween(status, startDate, endDate);
        } else if (status != null) {
            return gatepassRepository.findByStatus(status);
        } else if (startDate != null && endDate != null) {
            return gatepassRepository.findByCreatedAtBetween(startDate, endDate);
        } else {
            return gatepassRepository.findAll();
        }
    }
}
