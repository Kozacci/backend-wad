package pl.uwm.wateradventure.services.participants.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participants.security.AuthenticationResponse;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantRegisterDTO;

@Component
@RequiredArgsConstructor
class ParticipantCreator {

    public AuthenticationResponse register(ParticipantRegisterDTO participantRegisterDTO) {
        return null; // TODO;
    }

}
