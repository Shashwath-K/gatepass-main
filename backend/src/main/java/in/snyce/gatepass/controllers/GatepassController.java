package in.snyce.gatepass.controllers;

import in.snyce.gatepass.dto.ApiResponse;
import in.snyce.gatepass.model.Gatepass;
import in.snyce.gatepass.services.GatepassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/gatepasses")
public class GatepassController {

    private final GatepassService gatepassService;

    public GatepassController(GatepassService gatepassService) {
        this.gatepassService = gatepassService;
    }

    // 🔹 GET all gatepasses
    @GetMapping
    public ResponseEntity<ApiResponse<List<Gatepass>>> getAllGatepasses() {
        log.info("Fetching all gatepasses");
        log.debug("Attempting to call getAllGatepasses()");
        List<Gatepass> gatepasses = gatepassService.getAllGatepasses();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, gatepasses));
    }

    // 🔹 GET gatepass by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Gatepass>> getGatepassById(@PathVariable Integer id) {
        log.info("Fetching gatepass with ID: {}", id);
        Gatepass gatepass = gatepassService.getGatepassById(id);
        if (gatepass == null) {
            log.warn("Gatepass not found with ID: {}", id);
        }
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, gatepass));
    }

    // 🔹 CREATE new gatepass
    @PostMapping
    public ResponseEntity<ApiResponse<Gatepass>> createGatepass(@RequestBody Gatepass gatepass) {
        log.info("Creating new gatepass: {}", gatepass);
        log.debug("Gatepass object received for creation: {}", gatepass);
        Gatepass created = gatepassService.createGatepass(gatepass);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED, created), HttpStatus.CREATED);
    }

    // 🔹 UPDATE gatepass
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Gatepass>> updateGatepass(@PathVariable Integer id, @RequestBody Gatepass gatepass) {
        log.info("Updating gatepass with ID: {}", id);
        try {
            Gatepass updated = gatepassService.updateGatepass(id, gatepass);
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, updated));
        } catch (Exception e) {
            log.error("Error updating gatepass with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    // 🔹 DELETE gatepass
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteGatepass(@PathVariable Integer id) {
        log.warn("Deleting gatepass with ID: {}", id);
        gatepassService.deleteGatepass(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "Gatepass deleted successfully"));
    }

    // 🔹 TRACE level example (only visible if enabled explicitly)
    @GetMapping("/trace/example")
    public void traceExample() {
        log.trace("This is a TRACE level log message for diagnostics");
    }
}
