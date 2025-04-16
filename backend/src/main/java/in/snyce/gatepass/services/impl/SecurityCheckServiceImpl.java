package in.snyce.gatepass.services.impl;

import in.snyce.gatepass.model.SecurityCheck;
import in.snyce.gatepass.repositories.SecurityCheckRepository;
import in.snyce.gatepass.services.SecurityCheckService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityCheckServiceImpl implements SecurityCheckService {

    private final SecurityCheckRepository repository;

    public SecurityCheckServiceImpl(SecurityCheckRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SecurityCheck> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<SecurityCheck> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public SecurityCheck create(SecurityCheck check) {
        return repository.save(check);
    }

    @Override
    public Optional<SecurityCheck> update(Integer id, SecurityCheck check) {
        return repository.findById(id).map(existing -> {
            existing.setGatepassId(check.getGatepassId());
            return repository.save(existing);
        });
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
