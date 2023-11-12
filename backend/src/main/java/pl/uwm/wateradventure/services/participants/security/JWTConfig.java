package pl.uwm.wateradventure.services.participants.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.uwm.wateradventure.services.participants.ParticipantFacade;

@Configuration
@RequiredArgsConstructor
public class JWTConfig {

    private final ParticipantFacade participantFacade;

    @Bean
    public UserDetailsService userDetailsService() {
        return participantFacade::getParticipantByEmail;
    }
}
