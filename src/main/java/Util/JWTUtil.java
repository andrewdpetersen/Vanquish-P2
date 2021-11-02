package Util;

import Exceptions.AuthenticationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;

/**
 * JWTUtil
 * Class used for the creation/parsing of JWTs
 */
@Service
public class JWTUtil {
    private Key key;
    private final Logger logger = LogManager.getLogger();

    private void createKey(){
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String createJWT(HttpServletRequest request){
        createKey();

        // Build the java web token and return it
        return Jwts.builder().setIssuer(request.getRequestURL().toString())
                .setSubject(request.getParameter("username"))
                .signWith(key)
                .compact();
    }

    public String parseJWT(String token) {
        try {
            Jws<Claims> jwtClaim = Jwts.parserBuilder()  // Creates parser instance
                    .setSigningKey(key)             // Specify the key to verify this jws signature
                    .build()                        // Returns a new, thread-safe, parser
                    .parseClaimsJws(token);         // Parse the jws and return the original jws

            return jwtClaim.getBody().getSubject();
        } catch (JwtException e) {
            // JWT is invalid
            logger.info(String.format("Unauthorized user tried to barge their way in here! Don't worry, I caught this transgression. Error: %s",
                    e.getMessage()));
            throw new AuthenticationException();
        }
    }

    public Key getKey() {
        return key;
    }
}
