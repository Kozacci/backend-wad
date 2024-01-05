package pl.uwm.wateradventure.services.participants.crud;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantLoginDTO;
import pl.uwm.wateradventure.services.participants.security.JWTService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ParticipantReaderTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JWTService jwtService;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ParticipantRepository participantRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private ParticipantReader participantReader;

    @Test
    void shouldLoginSuccessfully() {
        // Given
        String email = "test@example.com";
        String password = "password123";
        ParticipantLoginDTO loginDTO = new ParticipantLoginDTO(email, password);

        String jwtToken = "jwtToken123";
        Cookie jwtCookie = new Cookie("JWT", jwtToken);

        ParticipantEntity mockedParticipant = new ParticipantEntity();
        mockedParticipant.setEmail(email);
        mockedParticipant.setPassword(passwordEncoder.encode(password));
        // When
        Authentication authentication = mock(Authentication.class);
        when(participantRepository.findByEmail(email)).thenReturn(Optional.of(mockedParticipant));
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtService.generateJsonWebToken(any(ParticipantEntity.class))).thenReturn(jwtToken);
        when(jwtService.createJwtCookie(jwtToken)).thenReturn(jwtCookie);
        ResponseEntity<?> responseEntity = participantReader.login(loginDTO, response);
        // Then
        verify(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken(email, password));
        verify(jwtService).generateJsonWebToken(any(ParticipantEntity.class));
        verify(jwtService).createJwtCookie(jwtToken);
        verify(response).addCookie(jwtCookie);
        assertEquals(ResponseEntity.ok().build(), responseEntity);
    }

    @Test
    void shouldLoginThrowBadCredentialsExceptionAndNeverCreateJWTCookie() {
        // Given
        String email = "nonexistent@example.com";
        String password = "incorrectPassword";
        ParticipantLoginDTO loginDTO = new ParticipantLoginDTO(email, password);
        // When
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));
        Exception exception = assertThrows(BadCredentialsException.class, () ->
                participantReader.login(loginDTO, response)
        );
        String expectedMessage = "Invalid credentials";
        String actualMessage = exception.getMessage();
        // Then
        assertEquals(actualMessage, expectedMessage);
        verify(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken(email, password));
        verify(jwtService, never()).generateJsonWebToken(any(ParticipantEntity.class));
        verify(jwtService, never()).createJwtCookie(anyString());
    }
}