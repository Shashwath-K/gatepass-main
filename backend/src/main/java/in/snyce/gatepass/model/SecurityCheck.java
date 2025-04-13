package in.snyce.gatepass.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "security_check")
//Security check model to verify the gatepass validity
public class SecurityCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "gatepass_id", nullable = false)
    private Integer gatepassId;

    @Column(name = "security_id", nullable = false)
    private Integer securityId;

    private String status;

    @Column(name = "check_date")
    private LocalDate checkDate;

    // Getter and Setter for id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getters and Setters for other fields
    public Integer getGatepassId() {
        return gatepassId;
    }

    public void setGatepassId(Integer gatepassId) {
        this.gatepassId = gatepassId;
    }

    public Integer getSecurityId() {
        return securityId;
    }

    public void setSecurityId(Integer securityId) {
        this.securityId = securityId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }
}
