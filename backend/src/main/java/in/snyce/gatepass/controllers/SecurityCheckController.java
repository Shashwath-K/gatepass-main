package in.snyce.gatepass.controllers;

import in.snyce.gatepass.dto.ApiResponse;
import in.snyce.gatepass.exceptions.ResourceNotFoundException;
import in.snyce.gatepass.model.SecurityCheck;
import in.snyce.gatepass.services.SecurityCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/security-checks")
public class SecurityCheckController {

    private static final Logger logger = LoggerFactory.getLogger(SecurityCheckController.class);
    private final SecurityCheckService securityCheckService;

    public SecurityCheckController(SecurityCheckService securityCheckService) {
        this.securityCheckService = securityCheckService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllSecurityChecks() {
        logger.info("Fetching all security checks");
        List<SecurityCheck> checks = securityCheckService.getAll();
        String message = checks.isEmpty() ? "No security checks found" : "Security checks retrieved";
        return ResponseEntity.ok(new ApiResponse(message, !checks.isEmpty()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getSecurityCheckById(@PathVariable Integer id) {
        logger.info("Fetching security check with ID: {}", id);
        securityCheckService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SecurityCheck", "id", id));
        return ResponseEntity.ok(new ApiResponse("Security check found", true));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createSecurityCheck(@RequestBody SecurityCheck check) {
        logger.info("Creating new security check for gatepass ID: {}", check.getGatepassId());
        securityCheckService.create(check);
        return new ResponseEntity<>(new ApiResponse("Security check created", true), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateSecurityCheck(@PathVariable Integer id, @RequestBody SecurityCheck check) {
        logger.info("Updating security check with ID: {}", id);
        securityCheckService.update(id, check)
                .orElseThrow(() -> new ResourceNotFoundException("SecurityCheck", "id", id));
        return ResponseEntity.ok(new ApiResponse("Security check updated", true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSecurityCheck(@PathVariable Integer id) {
        logger.info("Deleting security check with ID: {}", id);
        securityCheckService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Security check deleted", true));
    }
}
