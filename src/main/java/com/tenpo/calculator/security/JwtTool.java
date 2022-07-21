package com.tenpo.calculator.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


/**
 * Tool to create and validate JSON Web Tokens (JWT).
 */
@Component
public class JwtTool {

    private static final Logger logger = LoggerFactory.getLogger(JwtTool.class);
    @Value("${jwtTool.secretKey}")
    private String secretKey;

    /**
     * The TTL (Time to live) to set the JWT expiration
     */
    @Value("${jwtTool.ttlMillis}")
    private int ttlMillis;

    /**
     * Create a new JWT for the given username using the secretKey and TTL specified in the appliction.properties file.
     *
     * @param username User to authenticate.
     *
     * @return A JWT as String.
     */
    public String createJWT(String username) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    /**
     * Parse and validate the JWT claims to verify if it's valid.
     *
     * @param jwt The JWT as String to check.
     *
     * @return true if valid, else otherwise.
     */
    public boolean isJwtValid(String jwt) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
            return true;
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is not supported: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        }  catch (ExpiredJwtException e) {
            logger.error("JWT token has expired: {}", e.getMessage());
        }

        return false;
    }

    /**
     * Decrypt JWT and get the username.
     *
     * @param jwt The JWT as string.
     *
     * @return The username specified in the JWT.
     */
    public String getUsernameFromJwt(String jwt) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody().getSubject();

    }
}
