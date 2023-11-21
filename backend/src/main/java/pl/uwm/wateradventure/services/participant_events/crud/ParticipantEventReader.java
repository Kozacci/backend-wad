package pl.uwm.wateradventure.services.participant_events.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EntityNotFoundException;
import pl.uwm.wateradventure.models.participant_events.ParticipantEventEntity;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventInfoDTO;

import java.util.List;

@Component
@RequiredArgsConstructor
class ParticipantEventReader {

    private final ParticipantEventRepository repository;

    public ParticipantEventEntity getParticipantEventEntityById(Long participantEventEntityId) {
        return repository.findById(participantEventEntityId)
                .orElseThrow(() -> new EntityNotFoundException("participantEvent",
                        "ParticipantEventEntity with id: " + participantEventEntityId + " does not exists!"));
    }

    public List<ParticipantEventInfoDTO> getAssignedParticipantsForAnEventByEventId(Long eventId) {
        return repository.getAssignedParticipantsForAnEventByEventId(eventId);
    }
}
