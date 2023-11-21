package pl.uwm.wateradventure.exceptions.value_objects_exceptions;

import static pl.uwm.wateradventure.models.participants.Role.ADMIN;
import static pl.uwm.wateradventure.models.participants.Role.CLIENT;

public class InvalidRoleException extends RuntimeException {

    public static final String MESSAGE =
            String.format("User role must be one of '%s', '%s'.",
                    CLIENT, ADMIN);

    public InvalidRoleException() {
        super(String.format(MESSAGE));
    }


}
