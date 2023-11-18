package pl.uwm.wateradventure.services.participants.crud;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantEntityDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantLoginDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantRegisterDTO;

@Service
@RequiredArgsConstructor
public class ParticipantCRUDService {

    private final ParticipantCreator creator;
    private final ParticipantReader reader;
    private final ParticipantDeleter deleter;
    private final ParticipantUpdater updater;

    public ParticipantEntityDTO register(ParticipantRegisterDTO participantRegisterDTO) {
        return creator.register(participantRegisterDTO);
    }

    public ResponseEntity<?> login(ParticipantLoginDTO participantLoginDTO,
                                   HttpServletResponse response) {
        return reader.login(participantLoginDTO, response);
    }

    public ResponseEntity<?> logout(HttpServletResponse response) {
        return reader.logout(response);
    }

}
