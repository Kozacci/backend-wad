package pl.uwm.wateradventure.services.participant_events;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventEntityCreateDTO;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventEntityDTO;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventInfoDTO;
import pl.uwm.wateradventure.services.events.crud.EventCRUDService;
import pl.uwm.wateradventure.services.participant_events.crud.ParticipantEventCRUDService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ParticipantEventFacade {

    private final ParticipantEventCRUDService participantEventCRUDService;
    private final EventCRUDService eventCRUDService;

    public ParticipantEventEntityDTO signInForEvent(ParticipantEventEntityCreateDTO dto) {
        var event = eventCRUDService.getEventById(dto.eventId());
        return participantEventCRUDService.signInForEvent(event, dto);
    }

    public List<ParticipantEventInfoDTO> getAssignedParticipantsForAnEventByEventId(Long eventId) {
        return participantEventCRUDService.getAssignedParticipantsForAnEventByEventId(eventId);
    }

    public void deleteAssigningForEvent(Long participantEventId) {
        participantEventCRUDService.deleteAssigningForEvent(participantEventId);
    }

}
