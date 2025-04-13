package in.snyce.gatepass.services;

import in.snyce.gatepass.model.SecurityCheck;

import java.util.List;

public interface SecurityCheckService {
    List<SecurityCheck> getAll();
    SecurityCheck getById(Integer id);
    SecurityCheck create(SecurityCheck check);
    SecurityCheck update(Integer id, SecurityCheck check);
    void delete(Integer id);
}
