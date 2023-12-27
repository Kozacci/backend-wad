package pl.uwm.wateradventure.services.participants.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.EmailAlreadyExistsException;

@Component
@RequiredArgsConstructor
class ParticipantValidator {

    // TODO - more specific validation

    private final ParticipantRepository participantRepository;

    protected void checkIfEmailAlreadyExists(String email) {
        Boolean emailAlreadyExists = participantRepository.existsByEmail(email);
        if (emailAlreadyExists) {
            throw new EmailAlreadyExistsException("email", "Podany e-mail już istnieje");
        }
    }

}
