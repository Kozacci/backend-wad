package pl.uwm.wateradventure.services.participants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.services.participants.crud.ParticipantsCRUDService;

@Component
@RequiredArgsConstructor
public class ParticipantsFacade {

    private final ParticipantsCRUDService participantsCRUDService;


}
