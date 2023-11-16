package pl.uwm.wateradventure.services.participants.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EntityNotFoundException;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;
import pl.uwm.wateradventure.models.participants.security.AuthenticationResponse;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantLoginDTO;
import pl.uwm.wateradventure.services.participants.security.JWTService;

@Component
@RequiredArgsConstructor
class ParticipantReader {

    private final ParticipantRepository participantRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public ParticipantEntity getParticipantByEmail(String email) {
        return participantRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "participant", "User with email: " + email + " does not exist."
                        )
                );
    }

    public AuthenticationResponse login(ParticipantLoginDTO participantLoginDTO) {
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        participantLoginDTO.email(),
                        participantLoginDTO.password()
                )
        );
        // If authentication was successful then find the user and generate jwt for him + return it
        var loggingParticipant = getParticipantByEmail(participantLoginDTO.email());
        var jsonWebToken = jwtService.generateJsonWebToken(loggingParticipant);
        return AuthenticationResponse
                .builder()
                .token(jsonWebToken)
                .build();
    }

}
