package pl.uwm.wateradventure.services.participant_events;

import pl.uwm.wateradventure.models.events.EventCity;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.models.events.EventType;
import pl.uwm.wateradventure.models.participant_events.ParticipantEventEntity;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventEntityCreateDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ParticipantEventTestUtils {

    public static EventEntity getEvent() {
        return new EventEntity(
                EventType.PANIENSKI,
                150.0,
                LocalDateTime.of(2024, 1, 1, 12, 0, 0),
                EventCity.OLECKO,
                LocalTime.of(1, 30),
                10
        );
    }

    public static ParticipantEventEntityCreateDTO getParticipantEventEntityCreateDTOWith(Integer participantsNumber) {
        return ParticipantEventEntityCreateDTO.builder()
                .eventId(1L)
                .ordererEmail("janusz@email.com")
                .ordererFirstName("Janusz")
                .ordererLastName("Kowalski")
                .ordererPhoneNumber("505606707")
                .participantsNumber(participantsNumber)
                .build();
    }

    public static ParticipantEventEntity getParticipantEventEntityWithEventDatePastThreeDays() {
        var entity = new ParticipantEventEntity();
        var event = getEvent();
        event.setDate(LocalDateTime.now().minusDays(3));
        entity.setEvent(event);
        return entity;
    }
}
