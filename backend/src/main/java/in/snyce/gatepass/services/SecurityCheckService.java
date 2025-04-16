package in.snyce.gatepass.services;

import in.snyce.gatepass.model.SecurityCheck;

import java.util.List;
import java.util.Optional;

public interface SecurityCheckService {
    List<SecurityCheck> getAll();
    Optional<SecurityCheck> getById(Integer id);
    SecurityCheck create(SecurityCheck check);
    Optional<SecurityCheck> update(Integer id, SecurityCheck check);
    void delete(Integer id);
}
