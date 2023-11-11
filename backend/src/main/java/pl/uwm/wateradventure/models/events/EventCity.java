package pl.uwm.wateradventure.models.events;

import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidEventCityException;

import java.util.stream.Stream;

public enum EventCity {

    SOPOT("Sopot"),
    OLECKO("Olecko"),
    GDANSK("Gdańsk");

    public final String enumValue;

    EventCity(String enumValue) {
        this.enumValue = enumValue;
    }

    public static EventCity getEventCity(String givenValue) {
        return Stream
                .of(values())
                .filter(eventCity -> eventCity.enumValue.equals(givenValue))
                .findFirst()
                .orElseThrow(InvalidEventCityException::new);
    }

}
