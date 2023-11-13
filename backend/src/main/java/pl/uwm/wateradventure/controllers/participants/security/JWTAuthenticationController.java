package pl.uwm.wateradventure.controllers.participants.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.uwm.wateradventure.models.participants.security.AuthenticationResponse;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantLoginDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantRegisterDTO;
import pl.uwm.wateradventure.services.participants.ParticipantFacade;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class JWTAuthenticationController {

    private final ParticipantFacade participantFacade;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody ParticipantRegisterDTO participantRegisterDTO) {
        return ResponseEntity.ok(participantFacade.register(participantRegisterDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody ParticipantLoginDTO participantLoginDTO) {
        return ResponseEntity.ok(participantFacade.login(participantLoginDTO));
    }
}
