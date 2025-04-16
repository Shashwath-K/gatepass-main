package in.snyce.gatepass.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gatepass")
public class Gatepass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gatepassNumber;
    private LocalDate issueDate;
    private String block;
    private String siteAddress;

    // Building Manager
    private String buildingManagerName;
    private String buildingManagerContact;

    // Vendor Info
    private String vendorName;
    private String vendorContact;
    private String vendorAddress;

    // Asset info
    @ElementCollection
    @CollectionTable(name = "asset_details", joinColumns = @JoinColumn(name = "gatepass_id"))
    private List<AssetDetail> assetDetails = new ArrayList<>();

    // Approval
    private String requisitioner;
    private String pmRepresentative;
    private String reitRepresentative;
    private LocalDateTime approvalDateTime;

    // Movement
    private LocalDate outwardDate;
    private LocalDate inwardDate;

    // Status
    private String category; // Returnable or Non-Returnable
    private String status;   // Pending, Approved, Closed, etc.
    private LocalDate returnAcceptanceDate;

    // Timestamps
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Additional Fields (referenced in controller)
    private String employeeName;
    private String purpose;
    private LocalDateTime requestTime;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGatepassNumber() {
        return gatepassNumber;
    }

    public void setGatepassNumber(String gatepassNumber) {
        this.gatepassNumber = gatepassNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getBuildingManagerName() {
        return buildingManagerName;
    }

    public void setBuildingManagerName(String buildingManagerName) {
        this.buildingManagerName = buildingManagerName;
    }

    public String getBuildingManagerContact() {
        return buildingManagerContact;
    }

    public void setBuildingManagerContact(String buildingManagerContact) {
        this.buildingManagerContact = buildingManagerContact;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorContact() {
        return vendorContact;
    }

    public void setVendorContact(String vendorContact) {
        this.vendorContact = vendorContact;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public List<AssetDetail> getAssetDetails() {
        return assetDetails;
    }

    public void setAssetDetails(List<AssetDetail> assetDetails) {
        this.assetDetails = assetDetails;
    }

    public String getRequisitioner() {
        return requisitioner;
    }

    public void setRequisitioner(String requisitioner) {
        this.requisitioner = requisitioner;
    }

    public String getPmRepresentative() {
        return pmRepresentative;
    }

    public void setPmRepresentative(String pmRepresentative) {
        this.pmRepresentative = pmRepresentative;
    }

    public String getReitRepresentative() {
        return reitRepresentative;
    }

    public void setReitRepresentative(String reitRepresentative) {
        this.reitRepresentative = reitRepresentative;
    }

    public LocalDateTime getApprovalDateTime() {
        return approvalDateTime;
    }

    public void setApprovalDateTime(LocalDateTime approvalDateTime) {
        this.approvalDateTime = approvalDateTime;
    }

    public LocalDate getOutwardDate() {
        return outwardDate;
    }

    public void setOutwardDate(LocalDate outwardDate) {
        this.outwardDate = outwardDate;
    }

    public LocalDate getInwardDate() {
        return inwardDate;
    }

    public void setInwardDate(LocalDate inwardDate) {
        this.inwardDate = inwardDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getReturnAcceptanceDate() {
        return returnAcceptanceDate;
    }

    public void setReturnAcceptanceDate(LocalDate returnAcceptanceDate) {
        this.returnAcceptanceDate = returnAcceptanceDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    // Inner class remains unchanged
    @Embeddable
    public static class AssetDetail {
        private String assetName;
        private String materialDescription;
        private int quantity;
        private double value;
        private String remarks;

        public String getAssetName() {
            return assetName;
        }

        public void setAssetName(String assetName) {
            this.assetName = assetName;
        }

        public String getMaterialDescription() {
            return materialDescription;
        }

        public void setMaterialDescription(String materialDescription) {
            this.materialDescription = materialDescription;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
