package pl.uwm.wateradventure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.uwm.wateradventure.services.participants.crud.ParticipantRepository;

/**
 * Configuration class for JWT Authentication
 * It provides authentication for specific user found by email (from database)
 */
@Configuration
@RequiredArgsConstructor
public class JWTConfig {

    private final ParticipantRepository participantRepository;

    /**
     * Gets specific participant by email
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username ->
                participantRepository
                .findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found")
                );
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
     * Creates new encoder (BCrypt) with specific strength pattern
     * @return Encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
