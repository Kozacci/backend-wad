package pl.uwm.wateradventure.services.participants.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTService {

    // TODO -- generate another one (256/512bit)
    private static final String JSON_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";


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
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // we can configure below how long the token expires
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
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


}
