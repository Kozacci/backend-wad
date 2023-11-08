package pl.uwm.wateradventure.services.participants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.services.participants.crud.ParticipantCRUDService;

@Component
@RequiredArgsConstructor
public class ParticipantFacade {

    private final ParticipantCRUDService participantCRUDService;


}
