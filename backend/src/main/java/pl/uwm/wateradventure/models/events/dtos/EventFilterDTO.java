package pl.uwm.wateradventure.models.events.dtos;

import pl.uwm.wateradventure.models.events.EventCity;
import pl.uwm.wateradventure.models.events.EventType;

import java.time.LocalTime;

public record EventFilterDTO(Long eventId,
                             EventType type,
                             EventCity city,
                             Integer maxParticipantsNumber,
                             LocalTime duration,
                             String ordererLastName,
                             String ordererEmail) {
}
