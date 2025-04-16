package in.snyce.gatepass.controllers;

import in.snyce.gatepass.dto.ApiResponse;
import in.snyce.gatepass.exceptions.ResourceNotFoundException;
import in.snyce.gatepass.exceptions.ResponseMessage;
import in.snyce.gatepass.model.Gatepass;
import in.snyce.gatepass.services.GatepassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gatepasses")
public class GatepassController {

    @Autowired
    private GatepassService gatepassService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createGatepass(@RequestBody Gatepass gatepass) {
        Gatepass created = gatepassService.createGatepass(gatepass);
        return new ResponseEntity<>(
                new ApiResponse(
                        "active",
                        null,
                        ResponseMessage.GATEPASS_CREATED_SUCCESSFULLY.getMessage(),
                        true),
                HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse> getGatepasses(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<Gatepass> gatepasses = gatepassService.getGatepasses(status, startDate, endDate);
        Map<String, LocalDateTime> duration = new HashMap<>();
        duration.put("startDate", startDate != null ? startDate : LocalDateTime.now());
        duration.put("endDate", endDate != null ? endDate : LocalDateTime.now());

        String message = gatepasses.isEmpty() ? ResponseMessage.NO_DATA_FOUND.getMessage() : ResponseMessage.GATEPASSES_RETRIEVED_SUCCESSFULLY.getMessage();
        boolean success = !gatepasses.isEmpty();

        return ResponseEntity.ok(
                new ApiResponse(status != null ? status : "N/A", duration, message, success));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getGatepassById(@PathVariable Long id) {
        Gatepass gatepass = gatepassService.getGatepassById(id);
        if (gatepass == null) {
            throw new ResourceNotFoundException("Gatepass", "id", id);
        }
        return ResponseEntity.ok(
                new ApiResponse("N/A", null, ResponseMessage.GATEPASS_RETRIEVED_SUCCESSFULLY.getMessage(), true));
    }

    @PutMapping("/update/{id}/status")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest statusUpdate) {
        Gatepass updated = gatepassService.updateStatus(id, statusUpdate.getStatus());
        if (updated == null) {
            throw new ResourceNotFoundException("Gatepass", "id", id);
        }
        return ResponseEntity.ok(
                new ApiResponse("N/A", null, ResponseMessage.GATEPASS_UPDATED_SUCCESSFULLY.getMessage(), true));
    }

    public static class StatusUpdateRequest {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
