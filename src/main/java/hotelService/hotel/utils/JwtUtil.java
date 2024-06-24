package hotelService.hotel.utils;

import hotelService.hotel.entity.LoginDetails;
import hotelService.hotel.repository.LoginCredentialsRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {

    private String SECRET_KEY = "GAF7uWpTx7n+E0yHqodPkW7AdVmQRFSE";

    @Autowired
    private LoginCredentialsRepository loginCredRepo;


    public String isAuthorized(String authorizationToken) {

        String jwt = null;
        String userName = null;

        if (authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
            jwt = authorizationToken.substring(7);
            userName = extractUserName(jwt);
        }


        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String isValidUser = loginCredRepo.getUserName(userName);
            if (isValidUser != null && validateToken(jwt, isValidUser)) {
                UserDetails userDetails = getUserDetails(userName);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return isValidUser;
            }
        }
        return null;
    }

    public UserDetails getUserDetails(String userName) {
        LoginDetails details = loginCredRepo.getLoginDetailsByUserName(userName);
        return new User(details.getUserName(), "", new ArrayList<>());
    }

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

    private Claims extratAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String userName) {
        Map<String, Objects> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Objects> claims, String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, String isValidUser) {
        final String userName = extractUserName(token);
        return (userName.equals(isValidUser) && !isTokenExpired(token));
    }
}
