package pl.uwm.wateradventure.services.participants.crud;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantEntityDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantLoginDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantRegisterDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantUpdateDTO;

@Service
@RequiredArgsConstructor
public class ParticipantCRUDService {

    private final ParticipantCreator creator;
    private final ParticipantReader reader;
    private final ParticipantUpdater updater;
    // TODO - Deleting from User Profile
    private final ParticipantDeleter deleter;

    public ParticipantEntity getParticipantById(Long participantId) {
        return reader.getParticipantById(participantId);
    }

    public ParticipantEntity getParticipantByEmail(String email) {
        return reader.getParticipantByEmail(email);
    }

    public ParticipantEntityDTO register(ParticipantRegisterDTO participantRegisterDTO) {
        return creator.register(participantRegisterDTO);
    }

    public ResponseEntity<?> login(ParticipantLoginDTO participantLoginDTO,
                                   HttpServletResponse response) {
        return reader.login(participantLoginDTO, response);
    }

    public ParticipantEntityDTO updateParticipant(Long participantId, ParticipantUpdateDTO participantUpdateDTO) {
        var participantToUpdate = reader.getParticipantById(participantId);
        return updater.updateParticipant(participantToUpdate, participantUpdateDTO);
    }

}
