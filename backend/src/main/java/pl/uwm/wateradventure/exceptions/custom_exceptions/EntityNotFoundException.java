package pl.uwm.wateradventure.exceptions.custom_exceptions;

public class EntityNotFoundException extends RuntimeException {

    public String entityName;

    public EntityNotFoundException(String entityName, String message) {
        super(message);
        this.entityName = entityName;
    }

}
