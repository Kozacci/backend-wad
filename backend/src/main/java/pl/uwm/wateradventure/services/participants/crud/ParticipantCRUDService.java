package pl.uwm.wateradventure.services.participants.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.participants.security.AuthenticationResponse;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantLoginDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantRegisterDTO;

@Service
@RequiredArgsConstructor
public class ParticipantCRUDService {

    private final ParticipantCreator creator;
    private final ParticipantReader reader;
    private final ParticipantDeleter deleter;
    private final ParticipantUpdater updater;

    public AuthenticationResponse register(ParticipantRegisterDTO participantRegisterDTO) {
        return creator.register(participantRegisterDTO);
    }

    public AuthenticationResponse login(ParticipantLoginDTO participantLoginDTO) {
        return reader.login(participantLoginDTO);
    }

}
