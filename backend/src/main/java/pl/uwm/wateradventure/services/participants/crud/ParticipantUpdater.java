package pl.uwm.wateradventure.services.participants.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantEntityDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantUpdateDTO;

@Component
@RequiredArgsConstructor
class ParticipantUpdater {

    private final ParticipantRepository participantRepository;
    private final AuthenticationManager authenticationManager;

    public ParticipantEntityDTO updateParticipant(ParticipantEntity participantToUpdate, ParticipantUpdateDTO participantUpdateDTO) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        participantToUpdate.getEmail(),
                        participantUpdateDTO.getPassword()
                )
        );

        if (participantUpdateDTO.getFirstName() != null) {
            participantToUpdate.setFirstName(participantUpdateDTO.getFirstName());
        }
        if (participantUpdateDTO.getLastName() != null) {
            participantToUpdate.setLastName(participantUpdateDTO.getLastName());
        }
        if(participantUpdateDTO.getPhoneNumber() != null) {
            participantToUpdate.setPhoneNumber(participantUpdateDTO.getPhoneNumber());
        }

        participantRepository.saveAndFlush(participantToUpdate);
        return participantToUpdate.toDTO(); // TODO - do not return password there !
    }

}
