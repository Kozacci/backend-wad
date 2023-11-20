package pl.uwm.wateradventure.exceptions.value_objects_exceptions;

public class VarLengthExceededException extends RuntimeException {

    public String fieldName;

    public VarLengthExceededException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

}
