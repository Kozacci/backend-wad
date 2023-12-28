package pl.uwm.wateradventure.exceptions.custom_exceptions;

public class EventCancellationTimeoutException extends RuntimeException {
    public EventCancellationTimeoutException() {
        super("Event można odwołać conajmniej na 48h przed planowanym terminem.");
    }
}
