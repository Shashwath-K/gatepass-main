package in.snyce.gatepass.dto;

import java.time.LocalDateTime;
import java.util.Map;


public class ApiResponse {

    private String status; // Example: "active", "pending", etc.
    private Map<String, LocalDateTime> duration; // Includes startDate and endDate
    private String message; // Describes the result
    private boolean success; // Indicates if the operation was successful

    public ApiResponse() {}

    // For full responses with filtering context
    public ApiResponse(String status, Map<String, LocalDateTime> duration, String message, boolean success) {
        this.status = status;
        this.duration = duration;
        this.message = message;
        this.success = success;
    }

    // For simple success/failure responses (like create/update/delete)
    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, LocalDateTime> getDuration() {
        return duration;
    }

    public void setDuration(Map<String, LocalDateTime> duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

