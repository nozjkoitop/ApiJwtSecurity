package api.security;


import api.exception.CustomException;
import api.model.JwtRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component

public class JwtTokenProvider {

    Environment env = new StandardEnvironment();

    private final String secretKey = env.getProperty("SECRET_KEY");

    @Value("${jwt.token.expire-length}")
    private long validityInMilliseconds;

    public String createToken(@NonNull JwtRequest jwtRequest) {
        Date now = new Date();
        return Jwts.builder()
                .setIssuer(jwtRequest.getIssuer())
                .setSubject(jwtRequest.getSubject())
                .setExpiration(new Date(now.getTime() + validityInMilliseconds))
                .claim("roles", jwtRequest.getRoles())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(@NonNull String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            if (!claims.getIssuer().equals("GP")) {
                throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    Claims getClaims(@NonNull String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

}
