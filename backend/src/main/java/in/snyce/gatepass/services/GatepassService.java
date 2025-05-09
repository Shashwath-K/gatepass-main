package in.snyce.gatepass.services;

import in.snyce.gatepass.model.Gatepass;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface GatepassService {

    Gatepass createGatepass(Gatepass gatepass);

    List<Gatepass> getAllGatepasses();

    Gatepass getGatepassById(Long id);

    Gatepass updateGatepass(Long id, Gatepass updatedGatepass);

    void deleteGatepass(Long id);

    List<Gatepass> getGatepasses(String status, LocalDateTime startDate, LocalDateTime endDate);

    Page<Gatepass> searchGatepasses(String vendorName, int page, int size);

    Page<Gatepass> getGatepassesWithPaginationAndSorting(String status, LocalDateTime startDate, LocalDateTime endDate, org.springframework.data.domain.Pageable pageable);

    // NEW method added for full filter + pagination + sorting
    Page<Gatepass> getGatepasses(String status, LocalDateTime startDate, LocalDateTime endDate,
                                 int page, int size, String sortBy, String sortOrder);
}
