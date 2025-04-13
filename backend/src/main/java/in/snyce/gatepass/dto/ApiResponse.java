package in.snyce.gatepass.dto;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class ApiResponse<T> {
    private int statusCode;
    private String statusMessage;
    private LocalDateTime lastUpdated;
    private T data;

    // Constructor without lastUpdated
    public ApiResponse(HttpStatus status, T data) {
        this.statusCode = status.value();
        this.statusMessage = status.getReasonPhrase();
        this.data = data;
    }

    // Constructor with lastUpdated
    public ApiResponse(HttpStatus status, LocalDateTime lastUpdated, T data) {
        this.statusCode = status.value();
        this.statusMessage = status.getReasonPhrase();
        this.lastUpdated = lastUpdated;
        this.data = data;
    }

    // Getters and Setters
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    //Retrieve the lastUpdated date and time of the record
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    //Set the current date and time when data is modified
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
