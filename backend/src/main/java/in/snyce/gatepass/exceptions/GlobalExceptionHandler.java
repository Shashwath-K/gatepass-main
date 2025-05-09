package in.snyce.gatepass.exceptions;

import in.snyce.gatepass.dto.ApiResponse;
import in.snyce.gatepass.enums.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Handles application-wide exceptions using a consistent ApiResponse structure.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles resource not found exceptions.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ApiResponse response = new ApiResponse(
                status.value(),
                "not_found",
                buildDuration(),
                ex.getMessage()
        );

        return new ResponseEntity<>(response, status);
    }

    /**
     * Handles custom Gatepass business exceptions.
     */
    @ExceptionHandler(GatepassException.class)
    public ResponseEntity<ApiResponse> handleGatepassException(GatepassException ex, WebRequest request) {
        HttpStatus status = ex.getStatus();

        ApiResponse response = new ApiResponse(
                status.value(),
                "error",
                buildDuration(),
                ex.getMessage()
        );

        return new ResponseEntity<>(response, status);
    }

    /**
     * Handles generic exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneralException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiResponse response = new ApiResponse(
                status.value(),
                "error",
                buildDuration(),
                ResponseMessage.INTERNAL_SERVER_ERROR.getMessage()
        );

        return new ResponseEntity<>(response, status);
    }

    /**
     * Builds a consistent time duration structure.
     */
    private Map<String, LocalDateTime> buildDuration() {
        LocalDateTime now = LocalDateTime.now();
        return Map.of("startDate", now, "endDate", now);
    }
}
