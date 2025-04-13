package in.snyce.gatepass.controllers;

import in.snyce.gatepass.dto.ApiResponse;
import in.snyce.gatepass.model.SecurityCheck;
import in.snyce.gatepass.services.SecurityCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/security-checks")
public class SecurityCheckController {

    private static final Logger logger = LoggerFactory.getLogger(SecurityCheckController.class);
    private final SecurityCheckService securityCheckService;

    // Constructor to initialize SecurityCheckService
    public SecurityCheckController(SecurityCheckService securityCheckService) {
        this.securityCheckService = securityCheckService;
    }

    // Endpoint to fetch all security checks
    @GetMapping
    public ResponseEntity<ApiResponse<List<SecurityCheck>>> getAllSecurityChecks() {
        logger.info("Fetching all security checks");
        List<SecurityCheck> checks = securityCheckService.getAll();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, checks));
    }

    // Endpoint to fetch a security check by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SecurityCheck>> getSecurityCheckById(@PathVariable Integer id) {
        logger.info("Fetching security check with ID: {}", id);
        SecurityCheck check = securityCheckService.getById(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, check));
    }

    // Endpoint to create a new security check
    @PostMapping
    public ResponseEntity<ApiResponse<SecurityCheck>> createSecurityCheck(@RequestBody SecurityCheck check) {
        logger.info("Creating new security check for gatepass ID: {}", check.getGatepassId());
        SecurityCheck created = securityCheckService.create(check);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED, created), HttpStatus.CREATED);
    }

    // Endpoint to update an existing security check by ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SecurityCheck>> updateSecurityCheck(@PathVariable Integer id, @RequestBody SecurityCheck check) {
        logger.info("Updating security check with ID: {}", id);
        SecurityCheck updated = securityCheckService.update(id, check);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, updated));
    }

    // Endpoint to delete a security check by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteSecurityCheck(@PathVariable Integer id) {
        logger.info("Deleting security check with ID: {}", id);
        securityCheckService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "Security check deleted successfully"));
    }
}
