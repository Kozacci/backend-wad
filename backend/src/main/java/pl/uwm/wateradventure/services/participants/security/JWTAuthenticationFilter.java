package pl.uwm.wateradventure.services.participants.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Component that does filtering of every single HTTP request from backend server
 */
@Service
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jsonWebTokenService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    // It manages a sequence of filters
                                    // Note: One request could go through a series
                                    //       of filters before it reaches controller method
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().contains("/api/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }
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
        email = jsonWebTokenService.extractUsername(jsonWebToken);
         // if user is present, but hes not already authenticated
         if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
             // then we get our participant (as UserDetails) from database (our participant extends userDetails so its possible)
             UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
             // then we are checking if token for user is valid then update context and send it to dispatcher servlet, so he can use rest
             if(jsonWebTokenService.isJsonWebTokenValid(jsonWebToken, userDetails)) {
                 // If user and token are both valid, then we are able
                 // to create authentication token with its details(+ details from request) and authorities
                 UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                         userDetails,
                         null, // TODO userDetails.getUsername(), userDetails.getPassword(),
                         userDetails.getAuthorities()
                 );
                 authenticationToken.setDetails(
                         new WebAuthenticationDetailsSource().buildDetails(request)
                 );
                 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
             }
         }
         // After one filtering, we always need to pass the methods to call another filter in chain
         filterChain.doFilter(request, response);


    }
}
