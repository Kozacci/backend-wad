package pl.uwm.wateradventure.controllers.participant_events;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventEntityCreateDTO;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventEntityDTO;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventInfoDTO;
import pl.uwm.wateradventure.services.participant_events.ParticipantEventFacade;

import java.util.List;

/** REST Controller created in the needs of Create, Read, Update, Delete
 * and more complex operations for Participant Events Entity
 * @Endpoint: participant-events
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/participant-events")
class ParticipantEventController {

    private final ParticipantEventFacade facade;

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    ParticipantEventEntityDTO signInForEvent(@Valid @RequestBody ParticipantEventEntityCreateDTO dto) {
        return facade.signInForEvent(dto);
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    List<ParticipantEventInfoDTO> getAssignedParticipantsForAnEventByEventId(@PathVariable Long eventId) {
        return facade.getAssignedParticipantsForAnEventByEventId(eventId);
    }

    @DeleteMapping("/{participantEventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAssigningForEvent(@PathVariable Long participantEventId) {
        facade.deleteAssigningForEvent(participantEventId);
    }

}
