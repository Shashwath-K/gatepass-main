package in.snyce.gatepass.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "gatepasses")
public class Gatepass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer locationId;
    private Integer subLocationId;

    private LocalDate date;

    private String siteType;
    private String site;
    private String gatePassNo;
    private String documentNo;

    private Integer requestorId;
    private String requestorName;

    private String vendorName;
    private String vendorContactNo;
    private String siteAddress;

    private String builingMangerContact;
    private String vendorAddress;

    private String category;
    private String attachment;
    private LocalDateTime returnAcceptanceDate;

    @Column(columnDefinition = "TEXT")
    private String assetDetails;

    @Column(columnDefinition = "TEXT")
    private String inwardReport;

    @Column(columnDefinition = "TEXT")
    private String outwardReport;

    private String status;
    private Integer statusStep;

    @Column(columnDefinition = "TEXT")
    private String requestorClosedUpload;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(Integer subLocationId) {
        this.subLocationId = subLocationId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getGatePassNo() {
        return gatePassNo;
    }

    public void setGatePassNo(String gatePassNo) {
        this.gatePassNo = gatePassNo;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public Integer getRequestorId() {
        return requestorId;
    }

    public void setRequestorId(Integer requestorId) {
        this.requestorId = requestorId;
    }

    public String getRequestorName() {
        return requestorName;
    }

    public void setRequestorName(String requestorName) {
        this.requestorName = requestorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorContactNo() {
        return vendorContactNo;
    }

    public void setVendorContactNo(String vendorContactNo) {
        this.vendorContactNo = vendorContactNo;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getBuilingMangerContact() {
        return builingMangerContact;
    }

    public void setBuilingMangerContact(String builingMangerContact) {
        this.builingMangerContact = builingMangerContact;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public LocalDateTime getReturnAcceptanceDate() {
        return returnAcceptanceDate;
    }

    public void setReturnAcceptanceDate(LocalDateTime returnAcceptanceDate) {
        this.returnAcceptanceDate = returnAcceptanceDate;
    }

    public String getAssetDetails() {
        return assetDetails;
    }

    public void setAssetDetails(String assetDetails) {
        this.assetDetails = assetDetails;
    }

    public String getInwardReport() {
        return inwardReport;
    }

    public void setInwardReport(String inwardReport) {
        this.inwardReport = inwardReport;
    }

    public String getOutwardReport() {
        return outwardReport;
    }

    public void setOutwardReport(String outwardReport) {
        this.outwardReport = outwardReport;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusStep() {
        return statusStep;
    }

    public void setStatusStep(Integer statusStep) {
        this.statusStep = statusStep;
    }

    public String getRequestorClosedUpload() {
        return requestorClosedUpload;
    }

    public void setRequestorClosedUpload(String requestorClosedUpload) {
        this.requestorClosedUpload = requestorClosedUpload;
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
}
