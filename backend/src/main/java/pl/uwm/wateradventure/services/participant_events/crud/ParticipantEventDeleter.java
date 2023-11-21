package pl.uwm.wateradventure.services.participant_events.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participant_events.ParticipantEventEntity;

@Component
@RequiredArgsConstructor
class ParticipantEventDeleter {

    private final ParticipantEventRepository repository;

    public void deleteAssigningForEvent(ParticipantEventEntity participantEventEntity) {
        repository.delete(participantEventEntity);
    }

}
