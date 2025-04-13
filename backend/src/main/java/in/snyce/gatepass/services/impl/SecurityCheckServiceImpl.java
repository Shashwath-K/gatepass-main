package in.snyce.gatepass.services.impl;

import in.snyce.gatepass.model.SecurityCheck;
import in.snyce.gatepass.repositories.SecurityCheckRepository;
import org.springframework.stereotype.Service;
import in.snyce.gatepass.services.SecurityCheckService;

import java.util.List;

@Service
public class SecurityCheckServiceImpl implements SecurityCheckService {

    private final SecurityCheckRepository repository;

    public SecurityCheckServiceImpl(SecurityCheckRepository repository) {
        this.repository = repository;
    }

    // Fetch all security checks from the repository
    @Override
    public List<SecurityCheck> getAll() {
        return repository.findAll();
    }

    // Fetch a specific security check by its ID
    @Override
    public SecurityCheck getById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    // Create a new security check and save it to the repository
    @Override
    public SecurityCheck create(SecurityCheck check) {
        return repository.save(check);
    }

    // Update an existing security check by its ID
    @Override
    public SecurityCheck update(Integer id, SecurityCheck check) {
        check.setId(id);
        return repository.save(check);
    }

    // Delete a security check by its ID
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
