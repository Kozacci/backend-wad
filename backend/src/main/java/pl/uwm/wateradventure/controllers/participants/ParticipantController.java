package pl.uwm.wateradventure.controllers.participants;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.uwm.wateradventure.services.participants.ParticipantFacade;

/** REST Controller created in the needs of Create, Read, Update, Delete
 * and more complex operations for Participant Entity
 * @Endpoint: participants
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/participants")
class ParticipantController {

    private final ParticipantFacade participantFacade;


}
