package in.snyce.gatepass.exceptions;

public enum ResponseMessage {
    GATEPASS_CREATED_SUCCESSFULLY("Gatepass created successfully."),
    GATEPASSES_RETRIEVED_SUCCESSFULLY("Gatepasses get successfully."),
    NO_DATA_FOUND("No data found."),
    GATEPASS_RETRIEVED_SUCCESSFULLY("Gatepass successfully retrieved ."),
    GATEPASS_UPDATED_SUCCESSFULLY("Gatepass updated successfully."),
    GATEPASS_DELETED_SUCCESSFULLY("Gatepass deleted successfully."),
    INVALID_REQUEST("Invalid request."),
    INTERNAL_SERVER_ERROR("An internal error occurred. Please try again later.");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
