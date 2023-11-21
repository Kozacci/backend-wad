package pl.uwm.wateradventure.exceptions.custom_exceptions;

public class ParticipantAlreadySignedInException extends RuntimeException {

    public ParticipantAlreadySignedInException(String message) {
        super(message);
    }

}
