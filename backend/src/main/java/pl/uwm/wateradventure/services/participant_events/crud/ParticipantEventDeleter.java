package pl.uwm.wateradventure.services.participant_events.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EventCancellationTimeoutException;
import pl.uwm.wateradventure.models.participant_events.ParticipantEventEntity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
class ParticipantEventDeleter {

    private final ParticipantEventRepository repository;

    public void deleteAssigningForEvent(ParticipantEventEntity participantEventEntity) {
        LocalDateTime eventDate = participantEventEntity.getEvent().getDate();
        LocalDateTime nowDate = LocalDateTime.now();
        long differenceInHours = ChronoUnit.HOURS.between(nowDate, eventDate);
        if(differenceInHours < 48) {
            throw new EventCancellationTimeoutException();
        }
        repository.delete(participantEventEntity);
    }

}
