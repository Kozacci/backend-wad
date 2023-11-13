package pl.uwm.wateradventure.services.participants.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participants.security.AuthenticationResponse;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantLoginDTO;

@Component
@RequiredArgsConstructor
class ParticipantLogin {

    public AuthenticationResponse login(ParticipantLoginDTO participantLoginDTO) {
        return null; // TODO
    }

}
