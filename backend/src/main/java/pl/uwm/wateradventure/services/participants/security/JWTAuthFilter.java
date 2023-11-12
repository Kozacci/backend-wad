package pl.uwm.wateradventure.services.participants.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Component that does filtering of every single HTTP request from backend server
 *
 */
@Service // it may be just @Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTService jsonWebTokenService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    // It manages a sequence of filters
                                    // Note: One request could go through a series
                                    //       of filters before it reaches controller method
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

    // In each request there should be JWT Token in http Header ( it's in authorization tab )
        final String authorizationHeader = request.getHeader("Authorization");
        final String jsonWebToken;
        final String email;
        // If there is no auth header / it does not start with "Bearer " then do filtering and end authorization
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            // end method
            return;
        }
        jsonWebToken = authorizationHeader.substring(7); // extracting jwt token, skipping the bearer prefix
         email = jsonWebTokenService.extractEmail(jsonWebToken);
         // if user is already authenticated
         if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
             UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

         }


    }
}
