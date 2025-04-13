package in.snyce.gatepass.services;

import in.snyce.gatepass.model.Gatepass;
import java.util.List;

public interface GatepassService {
    List<Gatepass> getAllGatepasses();
    Gatepass getGatepassById(Integer id);
    Gatepass createGatepass(Gatepass gatepass);
    Gatepass updateGatepass(Integer id, Gatepass gatepass);
    void deleteGatepass(Integer id);
}
