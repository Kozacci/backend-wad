package pl.uwm.wateradventure.controllers.participants.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantEntityDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantLoginDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantRegisterDTO;
import pl.uwm.wateradventure.services.participants.ParticipantFacade;
import pl.uwm.wateradventure.services.participants.security.LogoutService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class JWTAuthenticationController {

    private final ParticipantFacade participantFacade;
    private final LogoutService logoutService;

    @PostMapping("/register")
    public ResponseEntity<ParticipantEntityDTO> register(@RequestBody ParticipantRegisterDTO participantRegisterDTO) {
        return ResponseEntity.ok(participantFacade.register(participantRegisterDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ParticipantLoginDTO participantLoginDTO,
                                   HttpServletResponse response) {
        return participantFacade.login(participantLoginDTO, response);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logoutService.logout(request, response, authentication);
    }

}
