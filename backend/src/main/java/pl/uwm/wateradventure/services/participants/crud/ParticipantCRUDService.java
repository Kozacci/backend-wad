package pl.uwm.wateradventure.services.participants.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;

@Service
@RequiredArgsConstructor
public class ParticipantCRUDService {

    private final ParticipantCreator creator;
    private final ParticipantLogin login;
    private final ParticipantReader reader;
    private final ParticipantDeleter deleter;
    private final ParticipantUpdater updater;

    public ParticipantEntity getParticipantByEmail(String email) {
        return reader.getParticipantByEmail(email);
    }


}
