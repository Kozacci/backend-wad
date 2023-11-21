package pl.uwm.wateradventure.services.participants.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTService {

    // TODO -- generate another one (256/512bit)
    // TODO -- on remote server set this as hidden variable (it should not be public)
    // TODO -- and before that, change value of JSON_KEY...
    private static final String JSON_KEY = "3odLKRxLr5nMe4NtkjQ+S/YSU2hjGyIQD0fNZtUWPpjtr8DT2pANt/BXilcI7/e6JmEokwi73o0TK0jmS+FOmBvs0hqE3/B/7Xug7/y+cSTFfV981EdFVJMtOLM2HCCMzamea8RohXL58m9+nCkBa2VupqGaoQEzcTZXVt6JGZuDCqYdTyapwOfrbV0VfuCjF/ppAA4A8v3x1zcG8F60hEe6FjFs+b6IVAiZrb6FOrwH/jZyQLm1DkLifru6CTa4hQ9PUJzlwB4vdfBNG0buKXBIsrrR09iIXyzbBKMcKxN1T2iNE8wM3hys9/zfjLkt6JypLRbz8ufvuCu0VfPb8gR2EaTMzKFIOWKlpDGmFSI=";


    // Extracts specific claim from token, for example expiration date of token
    public <T> T extractClaim(String jsonWebToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jsonWebToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jsonWebToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jsonWebToken)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JSON_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * It generates JWT Token without claims, just based on user details (method for registration)
     * @param: userDetails
     * @return string as jwt token
     */
    public String generateJsonWebToken(UserDetails userDetails) {
        return generateJsonWebToken(new HashMap<>(), userDetails);
    }

    /**
     * It generates JWT Token with claims, subject, issuedAt and expiration date parameters
     * @param: extraClaims
     * @param: userDetails
     * @return string as jwt token
     */
    public String generateJsonWebToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // we can configure below how long the token expires
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(2))) // Token expires in 2 hours time
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * It checks if JWT token is valid (it takes email from claims from JWT) and compares it to user's email that belongs to token
     * @param: jsonWebToken
     * @param: userDetails
     * @return true or false
     */
    public Boolean isJsonWebTokenValid(String jsonWebToken, UserDetails userDetails) {
        final String email = extractUsername(jsonWebToken);
        return (email.equals(userDetails.getUsername()) & !isJsonWebTokenExpired(jsonWebToken));
    }

    /**
     * It checks if JWT Token is still active (it takes it from JWT Claims)
     * @param: jsonWebToken
     * @return true or false
     */
    private Boolean isJsonWebTokenExpired(String jsonWebToken) {
        return extractExpiration(jsonWebToken).before(new Date());
    }

    /**
     * It extracts expiration date from JWT Claims
     * @param: jsonWebToken
     * @return date of jwt token expiration
     */
    private Date extractExpiration(String jsonWebToken) {
        return extractClaim(jsonWebToken, Claims::getExpiration);
    }

    /**
     * It extracts email from JWT Claims
     * @param: jsonWebToken
     * @return email of user with jwt token
     */
    public String extractUsername(String jsonWebToken) {
        return extractClaim(jsonWebToken, Claims::getSubject);
    }

    public Cookie createJwtCookie(String jwt) {
        Cookie jwtCookie = new Cookie("JWT", jwt);
        jwtCookie.setHttpOnly(true);
        // Cookie can be used from any URL
        jwtCookie.setPath("/");
        // For more secured HTTPS sites
         jwtCookie.setSecure(true);
        return jwtCookie;
    }

    public Optional<String> getJwtFromCookies(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }
        return Arrays.stream(request.getCookies())
                .filter(cookie -> "JWT".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst();
    }

}
