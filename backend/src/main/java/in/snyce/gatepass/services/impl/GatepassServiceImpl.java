package in.snyce.gatepass.services.impl;

import in.snyce.gatepass.model.Gatepass;
import in.snyce.gatepass.repositories.GatepassRepository;
import in.snyce.gatepass.services.GatepassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of GatepassService for managing gatepass operations.
 * Provides business logic and interacts with the GatepassRepository.
 */
@Service
public class GatepassServiceImpl implements GatepassService {

    @Autowired
    private GatepassRepository gatepassRepository;

    @Override
    public Gatepass createGatepass(Gatepass gatepass) {
        // Set the created timestamp
        gatepass.setCreatedAt(LocalDateTime.now());

        // Set the initial status
        gatepass.setStatus("Pending");

        // Auto-generate gatepassNo
        gatepass.setGatePassNo(generateGatepassNumber(gatepass));

        // Auto-generate or set default values for other fields
        if (gatepass.getUserId() == null) {
            gatepass.setUserId(101); // Replace with the current logged-in user's ID if available
        }

        if (gatepass.getLocationId() == null) {
            gatepass.setLocationId(1); // Set a default location ID
        }

        if (gatepass.getSubLocationId() == null) {
            gatepass.setSubLocationId(2); // Set a default sub-location ID
        }

        if (gatepass.getDocumentNo() == null) {
            gatepass.setDocumentNo("DOC006"); // You can generate this dynamically if needed
        }

        // Save the Gatepass object to the repository
        return gatepassRepository.save(gatepass);
    }


    @Override
    public List<Gatepass> getAllGatepasses() {
        return gatepassRepository.findAll();
    }

    @Override
    public Gatepass getGatepassById(Long id) {
        return gatepassRepository.findById(id).orElse(null);
    }

    @Override
    public Gatepass updateGatepass(Long id, Gatepass updatedGatepass) {
        Optional<Gatepass> optional = gatepassRepository.findById(id);
        if (optional.isPresent()) {
            Gatepass gatepass = optional.get();
            gatepass.setUserId(updatedGatepass.getUserId());
            gatepass.setLocationId(updatedGatepass.getLocationId());
            gatepass.setSubLocationId(updatedGatepass.getSubLocationId());
            gatepass.setDate(updatedGatepass.getDate());
            gatepass.setSiteType(updatedGatepass.getSiteType());
            gatepass.setSite(updatedGatepass.getSite());
            gatepass.setGatePassNo(updatedGatepass.getGatePassNo());
            gatepass.setDocumentNo(updatedGatepass.getDocumentNo());
            gatepass.setRequestorId(updatedGatepass.getRequestorId());
            gatepass.setRequestorName(updatedGatepass.getRequestorName());
            gatepass.setVendorName(updatedGatepass.getVendorName());
            gatepass.setVendorContactNo(updatedGatepass.getVendorContactNo());
            gatepass.setSiteAddress(updatedGatepass.getSiteAddress());
            gatepass.setBuilingMangerContact(updatedGatepass.getBuilingMangerContact());
            gatepass.setVendorAddress(updatedGatepass.getVendorAddress());
            gatepass.setCategory(updatedGatepass.getCategory());
            gatepass.setAttachment(updatedGatepass.getAttachment());
            gatepass.setReturnAcceptanceDate(updatedGatepass.getReturnAcceptanceDate());
            gatepass.setAssetDetails(updatedGatepass.getAssetDetails());
            gatepass.setInwardReport(updatedGatepass.getInwardReport());
            gatepass.setOutwardReport(updatedGatepass.getOutwardReport());
            gatepass.setStatus(updatedGatepass.getStatus());
            gatepass.setStatusStep(updatedGatepass.getStatusStep());
            gatepass.setRequestorClosedUpload(updatedGatepass.getRequestorClosedUpload());
            gatepass.setUpdatedAt(LocalDateTime.now());
            return gatepassRepository.save(gatepass);
        }
        return null;
    }

    @Override
    public Page<Gatepass> searchGatepasses(String vendorName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return gatepassRepository.findByVendorNameContainingIgnoreCase(vendorName, pageable);
    }

    @Override
    public void deleteGatepass(Long id) {
        if (gatepassRepository.existsById(id)) {
            gatepassRepository.deleteById(id);
        }
    }

    private String generateGatepassNumber(Gatepass gatepass) {
        String prefix = "TV";
        String siteType = gatepass.getSiteType() != null && gatepass.getSiteType().equalsIgnoreCase("SEZ") ? "SEZ" : "NonSEZ";
        String block = gatepass.getSite() != null ? gatepass.getSite().replaceAll("\\s+", "") : "Block-1";
        int currentYear = LocalDateTime.now().getYear();
        String academicYear = currentYear + "-" + String.valueOf(currentYear + 1).substring(2);
        long count = gatepassRepository.countByYear(currentYear);
        String serial = String.format("%04d", count + 1);
        return String.format("%s/%s/%s/%s/%s", prefix, siteType, block, academicYear, serial);
    }

    @Override
    public List<Gatepass> getGatepasses(String status, LocalDateTime startDate, LocalDateTime endDate) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "createdAt"));
        if (status != null && startDate != null && endDate != null) {
            return gatepassRepository.findByStatusAndCreatedAtBetween(status, startDate, endDate, pageable).getContent();
        } else if (status != null) {
            return gatepassRepository.findByStatus(status, pageable).getContent();
        } else if (startDate != null && endDate != null) {
            return gatepassRepository.findByCreatedAtBetween(startDate, endDate, pageable).getContent();
        } else {
            return gatepassRepository.findAll(pageable).getContent();
        }
    }

    @Override
    public Page<Gatepass> getGatepassesWithPaginationAndSorting(String status, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        if (status != null && startDate != null && endDate != null) {
            return gatepassRepository.findByStatusAndCreatedAtBetween(status, startDate, endDate, pageable);
        } else if (status != null) {
            return gatepassRepository.findByStatus(status, pageable);
        } else if (startDate != null && endDate != null) {
            return gatepassRepository.findByCreatedAtBetween(startDate, endDate, pageable);
        } else {
            return gatepassRepository.findAll(pageable);
        }
    }

    @Override
    public Page<Gatepass> getGatepasses(String status, LocalDateTime startDate, LocalDateTime endDate,
                                        int page, int size, String sortBy, String sortOrder) {
        Sort.Direction direction = sortOrder != null && sortOrder.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return gatepassRepository.searchGatepasses(
                status,
                null,       // vendorName
                startDate,
                endDate,
                pageable
        );
    }
}
