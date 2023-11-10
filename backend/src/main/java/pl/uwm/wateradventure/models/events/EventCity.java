package pl.uwm.wateradventure.models.events;

public enum EventCity {

    SOPOT("Sopot"),
    OLECKO("Olecko"),
    GDANSK("Gdańsk");

    public final String enumValue;

    EventCity(String enumValue) {
        this.enumValue = enumValue;
    }

}
