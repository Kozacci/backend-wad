package pl.uwm.wateradventure.exceptions.value_objects_exceptions;

public class InvalidDateException extends RuntimeException {

    public String fieldName;

    public InvalidDateException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }
}
