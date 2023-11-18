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
import java.util.Optional;

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

        // Getting JWT Token from Cookies
        Optional<String> jsonWebTokenFromCookies = jsonWebTokenService.getJwtFromCookies(request);

        // In each request there should be JWT Token in Cookies
        if (jsonWebTokenFromCookies.isPresent()) {
            String email = jsonWebTokenService.extractUsername(jsonWebTokenFromCookies.get());
            // if user is present, but he is not yet authenticated
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
                // If token is valid, put authorization into the context of application
                if (jsonWebTokenService.isJsonWebTokenValid(jsonWebTokenFromCookies.get(), userDetails)) {
                    // If user and token are both valid, then we are able
                    // to create authentication token with its details(+ details from request) and authorities
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null, // TODO userDetails.getUsername(), userDetails.getPassword(),
                            userDetails.getAuthorities() // admin/client
                    );
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
         }
         // After one filtering, we always need to pass the methods to call another filter in chain
         filterChain.doFilter(request, response);
    }

}
