package pl.uwm.wateradventure.exceptions.custom_exceptions;

public class CourseCancellationTimeoutException extends RuntimeException {
    public CourseCancellationTimeoutException() {
        super("Kurs można odwołać conajmniej na tydzień przed planowanym terminem.");
    }
}
