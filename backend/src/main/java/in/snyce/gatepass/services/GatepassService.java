package in.snyce.gatepass.services;

import in.snyce.gatepass.model.Gatepass;

import java.time.LocalDateTime;
import java.util.List;

public interface GatepassService {

    Gatepass createGatepass(Gatepass gatepass);
    List<Gatepass> getAllGatepasses();
    Gatepass getGatepassById(Long id);
    Gatepass updateStatus(Long id, String status);

    List<Gatepass> getGatepasses(String status, LocalDateTime startDate, LocalDateTime endDate);
}
