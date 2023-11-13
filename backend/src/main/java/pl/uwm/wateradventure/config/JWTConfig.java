package pl.uwm.wateradventure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.uwm.wateradventure.services.participants.ParticipantFacade;

/**
 * Configuration class for JWT Authentication
 * It provides authentication for specific user found by email (from database)
 */
@Configuration
@RequiredArgsConstructor
public class JWTConfig {

    private final ParticipantFacade participantFacade;

    /**
     * Gets specific participant by email
     * @return Participant entity
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return participantFacade::getParticipantByEmail;
    }

    /**
     * Creates new encoder (BCrypt) with specific strength pattern
     * @return Encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(1); // Strength of encoding
    }

    /**
     * Gets authentication manager from authentication config parameter
     * @param: AuthenticationConfiguration
     * @return Authentication Manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     *  Fetches user details and encodes password (it provides authentication)
     * @return Authentication provider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


}
