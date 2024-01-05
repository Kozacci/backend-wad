package pl.uwm.wateradventure.services.participants.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit5 + Mockito tests for authentication filtering and entire security context
 */
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

    /**
     * Used to reset security context after each launched test
     * Without it, we could not authenticate second time
     */
    @AfterEach
    public void resetSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void shouldAuthenticateUserWithValidToken() throws Exception {
        // Given
        String email = "user@example.com";
        String token = "validToken";
        UserDetails userDetails = User.withUsername(email)
                .password("password")
                .authorities("ROLE_USER")
                .build();

        // When
        when(request.getServletPath()).thenReturn("/api/questions/random");
        when(jsonWebTokenService.getJwtFromCookies(request)).thenReturn(Optional.of(token));
        when(jsonWebTokenService.extractUsername(token)).thenReturn(email);
        when(jsonWebTokenService.isJsonWebTokenValid(token, userDetails)).thenReturn(true);
        when(userDetailsService.loadUserByUsername(email)).thenReturn(userDetails);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then
        verify(filterChain, times(1)).doFilter(request, response);
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        assertNotNull(authentication);
        assertEquals(email, authentication.getName());
    }

    @Test
    void shouldNotAuthenticateWhenTokenNotPresent() throws Exception {
        // when
        when(request.getServletPath()).thenReturn("/api/questions/random");
        when(jsonWebTokenService.getJwtFromCookies(request)).thenReturn(Optional.empty());

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // then
        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void shouldNotAuthenticateWithInvalidToken() throws Exception {
        // given
        String token = "invalidToken";

        // when
        when(request.getServletPath()).thenReturn("/api/questions/random");
        when(jsonWebTokenService.getJwtFromCookies(request)).thenReturn(Optional.of(token));
        when(jsonWebTokenService.extractUsername(token)).thenReturn("user@example.com");
        when(jsonWebTokenService.isJsonWebTokenValid(any(), any())).thenReturn(false);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // then
        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void shouldPassThroughForLoginRequest() throws Exception {
        // when
        when(request.getServletPath()).thenReturn("/api/auth/login");

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // then
        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void shouldNotAuthenticateIfUserAlreadyAuthenticated() throws Exception {
        // given
        Authentication existingAuth = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        // when
        when(securityContext.getAuthentication()).thenReturn(existingAuth);
        SecurityContextHolder.setContext(securityContext);

        when(request.getServletPath()).thenReturn("/api/someEndpoint");
        when(jsonWebTokenService.getJwtFromCookies(request)).thenReturn(Optional.of("someToken"));

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // then
        verify(filterChain).doFilter(request, response);
        assertSame(existingAuth, SecurityContextHolder.getContext().getAuthentication());
    }

}