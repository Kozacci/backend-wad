package pl.uwm.wateradventure.services.participants.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class JWTAuthenticationFilterTest {

    @Mock
    private JWTService jsonWebTokenService;
    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @InjectMocks
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Test
    public void shouldAuthenticateUserWithValidToken() throws Exception {
        // Given
        String email = "user@example.com";
        String token = "validToken";
        UserDetails userDetails = User.withUsername(email)
                .password("password")
                .authorities("ROLE_USER")
                .build();

        when(request.getServletPath()).thenReturn("/api/questions/random");
        when(jsonWebTokenService.getJwtFromCookies(request)).thenReturn(Optional.of(token));
        when(jsonWebTokenService.extractUsername(token)).thenReturn(email);
        when(jsonWebTokenService.isJsonWebTokenValid(token, userDetails)).thenReturn(true);
        when(userDetailsService.loadUserByUsername(email)).thenReturn(userDetails);

        // When
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then
        verify(filterChain, times(1)).doFilter(request, response);
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        assertNotNull(authentication);
        assertEquals(email, authentication.getName());
    }
}