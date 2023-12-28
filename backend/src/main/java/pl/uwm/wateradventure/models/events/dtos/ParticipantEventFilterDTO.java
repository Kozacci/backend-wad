package pl.uwm.wateradventure.models.events.dtos;

import pl.uwm.wateradventure.models.events.EventCity;
import pl.uwm.wateradventure.models.events.EventType;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record ParticipantEventFilterDTO(Long eventId,
                                        EventType type,
                                        EventCity city,
                                        Double cost,
                                        Integer assignedParticipants,
                                        Integer maxParticipantsNumber,
                                        LocalDateTime date,
                                        LocalTime duration,
                                        Long participantEventId,
                                        String ordererLastName,
                                        String ordererEmail,
                                        Boolean isPaid) {
}
