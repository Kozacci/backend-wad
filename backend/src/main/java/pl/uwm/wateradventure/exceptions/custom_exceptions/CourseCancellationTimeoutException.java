package pl.uwm.wateradventure.exceptions.custom_exceptions;

public class CourseCancellationTimeoutException extends RuntimeException {
    public CourseCancellationTimeoutException() {
        super("Kurs można odwołać tylko przed zaksięgowaniem wpłaty.");
    }
}
