package pl.uwm.wateradventure.controllers.participants;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.uwm.wateradventure.services.participants.ParticipantsFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/participants")
class ParticipantsController {

    private final ParticipantsFacade participantsFacade;


}
