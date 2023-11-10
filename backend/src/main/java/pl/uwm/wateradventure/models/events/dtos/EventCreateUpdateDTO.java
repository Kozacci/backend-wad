package pl.uwm.wateradventure.models.events.dtos;

import lombok.Builder;
import pl.uwm.wateradventure.models.events.EventCity;
import pl.uwm.wateradventure.models.events.EventType;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record EventCreateUpdateDTO(EventType type,
                                   Double cost,
                                   LocalDateTime date,
                                   EventCity city,
                                   LocalTime duration,
                                   Integer maxParticipantsNumber) {

    @Builder
    public EventCreateUpdateDTO {}
}
