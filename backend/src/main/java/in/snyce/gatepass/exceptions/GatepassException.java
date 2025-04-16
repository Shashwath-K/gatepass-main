package in.snyce.gatepass.exceptions;

import org.springframework.http.HttpStatus;

public class GatepassException extends RuntimeException {
    private final ErrorCode code;
    private final ErrorStatus status;

    public GatepassException(ErrorCode code, String message, ErrorStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public ErrorCode getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return HttpStatus.valueOf(status.getHttpCode());
    }
}
