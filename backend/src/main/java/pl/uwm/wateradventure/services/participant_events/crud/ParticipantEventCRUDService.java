package pl.uwm.wateradventure.services.participant_events.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventEntityCreateDTO;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventEntityDTO;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventInfoDTO;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ParticipantEventCRUDService {

    private final ParticipantEventCreator creator;
    private final ParticipantEventReader reader;
    private final ParticipantEventDeleter deleter;

    public ParticipantEventEntityDTO signInForEvent(EventEntity event, ParticipantEventEntityCreateDTO dto) {
        return creator.signInForEvent(event, dto).toDTO();
    }

    public List<ParticipantEventInfoDTO> getAssignedParticipantsForAnEventByEventId(Long eventId) {
        return reader.getAssignedParticipantsForAnEventByEventId(eventId);
    }

    public void deleteAssigningForEvent(Long participantEventId) {
        var participantEventEntity = reader.getParticipantEventEntityById(participantEventId);
        deleter.deleteAssigningForEvent(participantEventEntity);
    }

}
