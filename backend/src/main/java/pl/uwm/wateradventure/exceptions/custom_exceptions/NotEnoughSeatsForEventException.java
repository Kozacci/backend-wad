package pl.uwm.wateradventure.exceptions.custom_exceptions;

public class NotEnoughSeatsForEventException extends RuntimeException {

    public NotEnoughSeatsForEventException(String message) {
        super(message);
    }

}
