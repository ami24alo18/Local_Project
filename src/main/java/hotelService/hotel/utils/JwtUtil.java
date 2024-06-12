package hotelService.hotel.utils;

import hotelService.hotel.model.CredentialsDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {

    private String SECRETE_KEY = "mykey";

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extratAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extratAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRETE_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String userName){
        Map<String, Objects> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Objects> claims, String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRETE_KEY)
                .compact();
    }

    public Boolean validateToken(String token, CredentialsDto creds){
        final String userName = extractUserName(token);
        return (userName.equals(creds.getUserName()) && !isTokenExpired(token));
    }
}
