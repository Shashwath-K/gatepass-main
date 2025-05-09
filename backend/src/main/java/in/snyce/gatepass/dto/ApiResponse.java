package in.snyce.gatepass.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiResponse {

    private String status; // e.g., "active", from DB
    private Map<String, LocalDateTime> duration; // Always present
    private Object data; // Present for GET, PUT
    private Map<String, Object> updatedFields; // Present only for PUT
    private String message; // Enum-based or custom message
    private boolean success; // true for 2xx codes

    // Default constructor
    public ApiResponse() {}

    /**
     * Constructor for GET or successful update (with data, no updated fields).
     */
    public ApiResponse(int statusCode, String status, Map<String, LocalDateTime> duration, String message, Object data) {
        this.status = status;
        this.duration = duration;
        this.message = message;
        this.success = statusCode >= 200 && statusCode < 300;
        this.data = data;
    }

    /**
     * Constructor for PUT update (with updated fields).
     */
    public ApiResponse(int statusCode, String status, Map<String, LocalDateTime> duration, String message, Object data, Map<String, Object> updatedFields) {
        this(statusCode, status, duration, message, data); // Calls the GET/PUT constructor
        this.updatedFields = updatedFields;
    }

    /**
     * Constructor for POST/DELETE without returning data.
     */
    public ApiResponse(int statusCode, String status, Map<String, LocalDateTime> duration, String message) {
        this.status = status;
        this.duration = duration;
        this.message = message;
        this.success = statusCode >= 200 && statusCode < 300;
    }

    // Getters and Setters

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getUpdatedFields() {
        return updatedFields;
    }

    public void setUpdatedFields(Map<String, Object> updatedFields) {
        this.updatedFields = updatedFields;
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
