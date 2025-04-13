package in.snyce.gatepass.services.impl;

import in.snyce.gatepass.model.Gatepass;
import in.snyce.gatepass.repositories.GatepassRepository;
import in.snyce.gatepass.services.GatepassService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatepassServiceImpl implements GatepassService {

    private final GatepassRepository gatepassRepository;

    public GatepassServiceImpl(GatepassRepository gatepassRepository) {
        this.gatepassRepository = gatepassRepository;
    }

    // Fetch all gatepasses from the repository
    @Override
    public List<Gatepass> getAllGatepasses() {
        return gatepassRepository.findAll();
    }

    // Fetch a specific gatepass by its ID
    @Override
    public Gatepass getGatepassById(Integer id) {
        return gatepassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gatepass not found"));
    }

    // Create a new gatepass and save it to the repository
    @Override
    public Gatepass createGatepass(Gatepass gatepass) {
        return gatepassRepository.save(gatepass);
    }

    // Update an existing gatepass by its ID
    @Override
    public Gatepass updateGatepass(Integer id, Gatepass gatepass) {
        Gatepass existing = getGatepassById(id);
        gatepass.setGatepassId(existing.getGatepassId());
        return gatepassRepository.save(gatepass);
    }

    // Delete a gatepass by its ID
    @Override
    public void deleteGatepass(Integer id) {
        gatepassRepository.deleteById(id);
    }
}
