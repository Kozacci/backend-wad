package pl.uwm.wateradventure.exceptions.value_objects_exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public String fieldName;

    public EmailAlreadyExistsException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

}
