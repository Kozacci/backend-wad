package pl.uwm.wateradventure.services.participants.crud;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EntityNotFoundException;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;
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

    public ResponseEntity<?> login(ParticipantLoginDTO participantLoginDTO,
                                   HttpServletResponse response) {
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
        // Create cookie with JWT token
        Cookie jwtCookie = jwtService.createJwtCookie(jsonWebToken);
        // Add token to cookies (via HttpServletResponse parameter)
        response.addCookie(jwtCookie);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie jwtCookie = new Cookie("JWT", null);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);
        response.addCookie(jwtCookie);
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }

}
