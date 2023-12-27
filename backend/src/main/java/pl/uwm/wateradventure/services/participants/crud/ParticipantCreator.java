package pl.uwm.wateradventure.services.participants.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantEntityDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantRegisterDTO;

@Component
@RequiredArgsConstructor
class ParticipantCreator {

    private final ParticipantRepository participantRepository;
    private final ParticipantValidator participantValidator;
    private final PasswordEncoder passwordEncoder;

    public ParticipantEntityDTO register(ParticipantRegisterDTO participantRegisterDTO) {

        var newParticipant =
                new ParticipantEntity(
                        participantRegisterDTO.firstName(),
                        participantRegisterDTO.lastName(),
                        participantRegisterDTO.email(),
                        passwordEncoder.encode(participantRegisterDTO.password()),
                        participantRegisterDTO.phoneNumber()
                );

        participantValidator.checkIfEmailAlreadyExists(newParticipant.getEmail());
        participantRepository.saveAndFlush(newParticipant);
        return newParticipant.toDTO();
    }

}
