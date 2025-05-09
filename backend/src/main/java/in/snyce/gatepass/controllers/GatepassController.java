package in.snyce.gatepass.controllers;

import in.snyce.gatepass.dto.ApiResponse;
import in.snyce.gatepass.enums.ResponseMessage;
import in.snyce.gatepass.exceptions.ResourceNotFoundException;
import in.snyce.gatepass.model.Gatepass;
import in.snyce.gatepass.services.GatepassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/gatepass")
public class GatepassController {

    @Autowired
    private GatepassService gatepassService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse> createGatepass(@RequestBody Gatepass gatepass) {
        log.info("Creating gatepass for userId: {}", gatepass.getUserId());
        Gatepass created = gatepassService.createGatepass(gatepass);

        Map<String, LocalDateTime> duration = Map.of(
                "startDate", LocalDateTime.now(),
                "endDate", LocalDateTime.now()
        );

        return new ResponseEntity<>(
                new ApiResponse(
                        HttpStatus.CREATED.value(),
                        created.getStatus(),
                        duration,
                        ResponseMessage.GATEPASS_CREATED_SUCCESSFULLY.getMessage()
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getGatepasses(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        log.info("Fetching gatepasses with status: {}, startDate: {}, endDate: {}, sortBy: {}, sortOrder: {}", status, startDate, endDate, sortBy, sortOrder);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortOrder) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        Page<Gatepass> gatepassPage = gatepassService.getGatepasses(status, startDate, endDate, page, size, sortBy, sortOrder);
        List<Gatepass> gatepasses = gatepassPage.getContent();

        Map<String, LocalDateTime> duration = new HashMap<>();
        duration.put("startDate", startDate != null ? startDate : LocalDateTime.now());
        duration.put("endDate", endDate != null ? endDate : LocalDateTime.now());

        String message = gatepasses.isEmpty()
                ? ResponseMessage.NO_DATA_FOUND.getMessage()
                : ResponseMessage.GATEPASSES_RETRIEVED_SUCCESSFULLY.getMessage();

        Map<String, Object> paginationMeta = new HashMap<>();
        paginationMeta.put("totalElements", gatepassPage.getTotalElements());
        paginationMeta.put("totalPages", gatepassPage.getTotalPages());
        paginationMeta.put("currentPage", gatepassPage.getNumber());
        paginationMeta.put("pageSize", gatepassPage.getSize());

        return ResponseEntity.ok(
                new ApiResponse(
                        HttpStatus.OK.value(),
                        gatepasses.isEmpty() ? null : gatepasses.get(0).getStatus(),
                        duration,
                        message,
                        gatepasses.isEmpty() ? null : gatepasses,
                        paginationMeta
                )
        );
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getGatepassById(@PathVariable Long id) {
        log.info("Fetching gatepass with ID: {}", id);
        Gatepass gatepass = gatepassService.getGatepassById(id);
        if (gatepass == null) {
            log.warn("Gatepass not found with ID: {}", id);
            throw new ResourceNotFoundException("Gatepass", "id", id);
        }

        Map<String, LocalDateTime> duration = Map.of(
                "startDate", LocalDateTime.now(),
                "endDate", LocalDateTime.now()
        );

        return ResponseEntity.ok(
                new ApiResponse(
                        HttpStatus.OK.value(),
                        gatepass.getStatus(),
                        duration,
                        ResponseMessage.GATEPASS_RETRIEVED_SUCCESSFULLY.getMessage(),
                        gatepass
                )
        );
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateGatepass(@PathVariable Long id, @RequestBody Gatepass updatedGatepass) {
        log.info("Updating gatepass with ID: {}", id);

        Gatepass existing = gatepassService.getGatepassById(id);
        if (existing == null) {
            log.warn("Gatepass not found for update with ID: {}", id);
            throw new ResourceNotFoundException("Gatepass", "id", id);
        }

        Gatepass updated = gatepassService.updateGatepass(id, updatedGatepass);

        Map<String, Object> updatedFields = getUpdatedFields(existing, updated);

        Map<String, LocalDateTime> duration = Map.of(
                "startDate", updated.getUpdatedAt() != null ? updated.getUpdatedAt() : LocalDateTime.now(),
                "endDate", LocalDateTime.now()
        );

        return ResponseEntity.ok(
                new ApiResponse(
                        HttpStatus.OK.value(),
                        updated.getStatus(),
                        duration,
                        ResponseMessage.GATEPASS_UPDATED_SUCCESSFULLY.getMessage(),
                        updated,
                        updatedFields
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteGatepass(@PathVariable Long id) {
        log.info("Deleting gatepass with ID: {}", id);
        Gatepass gatepass = gatepassService.getGatepassById(id);
        if (gatepass == null) {
            log.warn("Gatepass not found for deletion with ID: {}", id);
            throw new ResourceNotFoundException("Gatepass", "id", id);
        }

        gatepassService.deleteGatepass(id);

        Map<String, LocalDateTime> duration = Map.of(
                "startDate", LocalDateTime.now(),
                "endDate", LocalDateTime.now()
        );

        return ResponseEntity.ok(
                new ApiResponse(
                        HttpStatus.OK.value(),
                        gatepass.getStatus(),
                        duration,
                        ResponseMessage.GATEPASS_DELETED_SUCCESSFULLY.getMessage()
                )
        );
    }

    /**
     * Search gatepasses by vendor name with pagination.
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchGatepasses(
            @RequestParam(defaultValue = "") String vendorName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Searching gatepasses by vendorName='{}', page={}, size={}", vendorName, page, size);

        Page<Gatepass> gatepassPage = gatepassService.searchGatepasses(vendorName, page, size);

        Map<String, Object> paginationMeta = new HashMap<>();
        paginationMeta.put("totalElements", gatepassPage.getTotalElements());
        paginationMeta.put("totalPages", gatepassPage.getTotalPages());
        paginationMeta.put("currentPage", gatepassPage.getNumber());
        paginationMeta.put("pageSize", gatepassPage.getSize());

        Map<String, LocalDateTime> duration = Map.of(
                "startDate", LocalDateTime.now(),
                "endDate", LocalDateTime.now()
        );

        return ResponseEntity.ok(
                new ApiResponse(
                        HttpStatus.OK.value(),
                        !gatepassPage.isEmpty() ? gatepassPage.getContent().get(0).getStatus() : null,
                        duration,
                        ResponseMessage.GATEPASSES_RETRIEVED_SUCCESSFULLY.getMessage(),
                        gatepassPage.getContent(),
                        paginationMeta
                )
        );
    }

    private Map<String, Object> getUpdatedFields(Gatepass original, Gatepass updated) {
        Map<String, Object> changes = new HashMap<>();
        if (!Objects.equals(original.getUserId(), updated.getUserId())) changes.put("userId", updated.getUserId());
        if (!Objects.equals(original.getLocationId(), updated.getLocationId())) changes.put("locationId", updated.getLocationId());
        if (!Objects.equals(original.getSubLocationId(), updated.getSubLocationId())) changes.put("subLocationId", updated.getSubLocationId());
        if (!Objects.equals(original.getDate(), updated.getDate())) changes.put("date", updated.getDate());
        if (!Objects.equals(original.getSiteType(), updated.getSiteType())) changes.put("siteType", updated.getSiteType());
        if (!Objects.equals(original.getSite(), updated.getSite())) changes.put("site", updated.getSite());
        if (!Objects.equals(original.getGatePassNo(), updated.getGatePassNo())) changes.put("gatePassNo", updated.getGatePassNo());
        if (!Objects.equals(original.getDocumentNo(), updated.getDocumentNo())) changes.put("documentNo", updated.getDocumentNo());
        if (!Objects.equals(original.getRequestorId(), updated.getRequestorId())) changes.put("requestorId", updated.getRequestorId());
        if (!Objects.equals(original.getRequestorName(), updated.getRequestorName())) changes.put("requestorName", updated.getRequestorName());
        if (!Objects.equals(original.getVendorName(), updated.getVendorName())) changes.put("vendorName", updated.getVendorName());
        if (!Objects.equals(original.getVendorContactNo(), updated.getVendorContactNo())) changes.put("vendorContactNo", updated.getVendorContactNo());
        if (!Objects.equals(original.getSiteAddress(), updated.getSiteAddress())) changes.put("siteAddress", updated.getSiteAddress());
        if (!Objects.equals(original.getBuilingMangerContact(), updated.getBuilingMangerContact())) changes.put("builingMangerContact", updated.getBuilingMangerContact());
        if (!Objects.equals(original.getVendorAddress(), updated.getVendorAddress())) changes.put("vendorAddress", updated.getVendorAddress());
        if (!Objects.equals(original.getCategory(), updated.getCategory())) changes.put("category", updated.getCategory());
        if (!Objects.equals(original.getAttachment(), updated.getAttachment())) changes.put("attachment", updated.getAttachment());
        if (!Objects.equals(original.getReturnAcceptanceDate(), updated.getReturnAcceptanceDate())) changes.put("returnAcceptanceDate", updated.getReturnAcceptanceDate());
        if (!Objects.equals(original.getAssetDetails(), updated.getAssetDetails())) changes.put("assetDetails", updated.getAssetDetails());
        if (!Objects.equals(original.getInwardReport(), updated.getInwardReport())) changes.put("inwardReport", updated.getInwardReport());
        if (!Objects.equals(original.getOutwardReport(), updated.getOutwardReport())) changes.put("outwardReport", updated.getOutwardReport());
        if (!Objects.equals(original.getStatus(), updated.getStatus())) changes.put("status", updated.getStatus());
        if (!Objects.equals(original.getStatusStep(), updated.getStatusStep())) changes.put("statusStep", updated.getStatusStep());
        if (!Objects.equals(original.getRequestorClosedUpload(), updated.getRequestorClosedUpload())) changes.put("requestorClosedUpload", updated.getRequestorClosedUpload());
        return changes;
    }

    public static class StatusUpdateRequest {
        private String status;
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}