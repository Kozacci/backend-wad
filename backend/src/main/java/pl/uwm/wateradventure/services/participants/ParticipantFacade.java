package pl.uwm.wateradventure.services.participants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;
import pl.uwm.wateradventure.services.participants.crud.ParticipantCRUDService;

@Component
@RequiredArgsConstructor
public class ParticipantFacade {

    private final ParticipantCRUDService participantCRUDService;

    public ParticipantEntity getParticipantByEmail(String email) {
        return participantCRUDService.getParticipantByEmail(email);
    }

}
