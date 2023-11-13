package pl.uwm.wateradventure.services.participants.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;
import pl.uwm.wateradventure.models.participants.security.AuthenticationResponse;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantRegisterDTO;
import pl.uwm.wateradventure.services.participants.security.JWTService;

@Component
@RequiredArgsConstructor
class ParticipantCreator {

    private final ParticipantRepository participantRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthenticationResponse register(ParticipantRegisterDTO participantRegisterDTO) {

        var newParticipant =
                new ParticipantEntity(
                        participantRegisterDTO.firstName(),
                        participantRegisterDTO.lastName(),
                        participantRegisterDTO.email(),
                        passwordEncoder.encode(participantRegisterDTO.password()),
                        participantRegisterDTO.phoneNumber()
                );
        participantRepository.saveAndFlush(newParticipant);

        var jsonWebToken = jwtService.generateJsonWebToken(newParticipant);
        return AuthenticationResponse
                .builder()
                .token(jsonWebToken)
                .build();
    }

}
