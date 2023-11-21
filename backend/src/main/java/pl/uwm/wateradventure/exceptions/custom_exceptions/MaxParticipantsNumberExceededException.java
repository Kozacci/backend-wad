package pl.uwm.wateradventure.exceptions.custom_exceptions;

public class MaxParticipantsNumberExceededException extends RuntimeException {

    public MaxParticipantsNumberExceededException() {
        super("Brak dostępnych miejsc na ten kurs.");
    }

}
