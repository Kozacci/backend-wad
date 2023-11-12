package pl.uwm.wateradventure.services.participants.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EntityNotFoundException;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;

@Component
@RequiredArgsConstructor
class ParticipantReader {

    private final ParticipantRepository participantRepository;

    public ParticipantEntity getParticipantByEmail(String email) {
        return participantRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "participant", "User with email: " + email + " does not exist."
                        )
                );
    }

}
