package in.snyce.gatepass.exceptions;

import in.snyce.gatepass.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ApiResponse response = new ApiResponse(
                "N/A",
                Map.of(
                        "startDate", LocalDateTime.now(),
                        "endDate", LocalDateTime.now()
                ),
                ex.getMessage(),
                false
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GatepassException.class)
    public ResponseEntity<ApiResponse> handleGatepassException(GatepassException ex, WebRequest request) {
        ApiResponse response = new ApiResponse(
                "N/A",
                Map.of(
                        "startDate", LocalDateTime.now(),
                        "endDate", LocalDateTime.now()
                ),
                ex.getMessage(),
                false
        );
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneralException(Exception ex, WebRequest request) {
        ApiResponse response = new ApiResponse(
                "N/A",
                Map.of(
                        "startDate", LocalDateTime.now(),
                        "endDate", LocalDateTime.now()
                ),
                ResponseMessage.INTERNAL_SERVER_ERROR.getMessage(),
                false
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
