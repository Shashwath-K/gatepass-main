package in.snyce.gatepass.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "gatepass")
// Gatepass model based on the proposed E-R diagram
public class Gatepass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gatepassId;

    private String gatepassNo;
    private String park;
    private String building;
    private LocalDate requestDate;
    private String vendorName;
    private String materialDescription;
    private String category;
    private LocalDate returnAcceptanceDate;
    private String outwardReceipt;
    private String inwardReceipt;
    private String imageAttachment;
    private String status;

    private Integer requestedBy;
    private Integer approvedBy;

    @LastModifiedDate
    private LocalDateTime lastUpdated;
}
