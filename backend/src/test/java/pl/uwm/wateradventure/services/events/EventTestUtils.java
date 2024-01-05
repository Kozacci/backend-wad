package pl.uwm.wateradventure.services.events;

import pl.uwm.wateradventure.models.events.EventCity;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.models.events.EventType;
import pl.uwm.wateradventure.models.participant_events.ParticipantEventEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;


public class EventTestUtils {

    public static EventEntity getEvent() {
        return new EventEntity(
                EventType.KAWALERSKI,
                500.0,
                LocalDateTime.now().minusDays(5),
                EventCity.GDANSK,
                LocalTime.now(),
                10
        );
    }

    public static void addParticipantsToEvent(EventEntity event, int numberOfParticipants) {
        for (int i = 0; i < numberOfParticipants; i++) {
            ParticipantEventEntity participantEvent = new ParticipantEventEntity();
            event.getEventParticipants().add(participantEvent);
        }
    }

}
