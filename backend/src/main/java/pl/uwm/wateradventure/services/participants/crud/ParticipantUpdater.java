package pl.uwm.wateradventure.services.participants.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantEntityDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantUpdateDTO;

@Component
@RequiredArgsConstructor
class ParticipantUpdater {

    private final ParticipantRepository participantRepository;
    private final PasswordEncoder passwordEncoder;

    public ParticipantEntityDTO updateParticipant(ParticipantEntity participantToUpdate, ParticipantUpdateDTO participantUpdateDTO) {
        if (participantUpdateDTO.getFirstName() != null) {
            participantToUpdate.setFirstName(participantUpdateDTO.getFirstName());
        }
        if (participantUpdateDTO.getLastName() != null) {
            participantToUpdate.setLastName(participantUpdateDTO.getLastName());
        }
        if (participantUpdateDTO.getEmail() != null) {
            participantToUpdate.setEmail(participantUpdateDTO.getEmail());
        }
        if (participantUpdateDTO.getPassword() != null) {
            participantToUpdate.setPassword(passwordEncoder.encode(participantUpdateDTO.getPassword()));
        }
        if(participantUpdateDTO.getPhoneNumber() != null) {
            participantToUpdate.setPhoneNumber(participantUpdateDTO.getPhoneNumber());
        }
        participantRepository.saveAndFlush(participantToUpdate);
        return participantToUpdate.toDTO();
    }

}
