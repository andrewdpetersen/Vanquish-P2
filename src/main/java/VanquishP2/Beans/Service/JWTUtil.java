package VanquishP2.Beans.Service;

import VanquishP2.Beans.Models.User;
import VanquishP2.Exceptions.AuthenticationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.sql.Date;

/**
 * JWTUtil
 * Class used for the creation/parsing of JWTs
 */
@Service
public class JWTUtil {

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.prefix}")
    private String prefix;

    @Value("#{24*60*60*1000}")
    private int expiration;

    private Key key;

    public void JWTUtil (){
        createKey();
    }

    private void createKey(){
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String createJWT(User user){
        // Build the java web token and return it
        return Jwts.builder()
                .setIssuer("Vanquish")
                .setSubject(user.getUserInfo().getUsername())
                .signWith(key)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    public Jws<Claims> parseJWT(String token) throws AuthenticationException {
        return Jwts.parserBuilder()             // Creates parser instance
                .setSigningKey(key)             // Specify the key to verify this jws signature
                .build()                        // Returns a new, thread-safe, parser
                .parseClaimsJws(token);
    }

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public Key getSigningKey() {
        return key;
    }
}
