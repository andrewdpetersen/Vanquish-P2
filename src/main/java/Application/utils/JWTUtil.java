package Application.utils;

import Application.exceptions.AuthenticationException;
import Application.models.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;

/**
 * JWTUtil
 * Class used for the creation/parsing of JWTs
 * @author Kollier Martin
 * @date 11/1/2021
 */
@Component
public class JWTUtil {

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.prefix}")
    private String prefix;

    @Value("#{24*60*60*1000}")
    private int expiration;

    private Key key;

    public JWTUtil(){
        createKey();
    }

    private void createKey(){
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String createJWT(UserInfo userInfo){
        return Jwts.builder()
                .setIssuer("Vanquish")
                .setSubject(userInfo.getUsername())
                .signWith(key)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    public Claims parseJWT(String token) throws AuthenticationException {
        return Jwts.parserBuilder()             // Creates parser instance
                .setSigningKey(key)             // Specify the key to verify this jws signature
                .build()                        // Returns a new, thread-safe, parser
                .parseClaimsJws(token)
                .getBody();
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

    public Key getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "JWTUtil {\n" +
                "header: " + header + ",\n" +
                "prefix: " + prefix + ",\n" +
                "expiration: " + expiration + ",\n" +
                "key: " + key + ",\n" +
                '}';
    }

    public void printVars(){
        System.out.println(this);
    }
}

