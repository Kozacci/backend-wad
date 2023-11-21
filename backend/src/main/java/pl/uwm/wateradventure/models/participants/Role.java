package pl.uwm.wateradventure.models.participants;

import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidRoleException;

import java.util.stream.Stream;

public enum Role {

    CLIENT("Klient"),
    ADMIN("Admin");

    public final String enumValue;

    Role(String enumValue) {
        this.enumValue = enumValue;
    }

    public static Role getRole(String givenValue) {
        return Stream
                .of(values())
                .filter(role -> role.enumValue.equals(givenValue))
                .findFirst()
                .orElseThrow(InvalidRoleException::new);
    }

}
