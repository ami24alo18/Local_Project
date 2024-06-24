package hotelService.hotel.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class JwtService {

    private static final String SECRETE_KEY = "GAF7uWpTx7n+E0yHqodPkW7AdVmQRFSE"; // Replace with your actual secret key

    public String decodeJwtToken(String jwtToken) {
        try {
            byte[] keyBytes = Base64.getEncoder().encode(SECRETE_KEY.getBytes());
            Claims claims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(keyBytes))
                    .parseClaimsJws(jwtToken)
                    .getBody();

            return claims.getSubject(); // Assuming userId is stored in the subject field
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
}
