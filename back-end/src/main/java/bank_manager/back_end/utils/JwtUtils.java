package bank_manager.back_end.utils;

import bank_manager.back_end.enums.EntityType;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

import static java.lang.Long.parseLong;


public class JwtUtils {

    private static final long EXPIRATION_TIME = 3600000;
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public static String generateToken(Long entityId, String entityType) {
        return Jwts.builder()
                .setSubject(String.valueOf(entityId))
                .claim("entityType", entityType)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean isTokenValid(String token) {
        Claims claims = getAllClaims(token);
        final Long entityId = getEntityId(claims);
        final EntityType entityType = getEntityType(claims);
        final Date expirationDate = getExpirationDate(claims);

        if (entityId == null || entityType == null || expirationDate == null) return false;
        if (isTokenExpired(expirationDate)) return false;

        return true;
    }

    public static Long getEntityId(Claims claims) {
        return parseLong(claims.getSubject());
    }

    public static EntityType getEntityType(Claims claims) {
        String entityType = claims.get("entityType", String.class);
        if (Objects.equals(entityType, EntityType.USER.name())) return EntityType.USER;
        if (Objects.equals(entityType, EntityType.ADMIN.name())) return EntityType.ADMIN;
        return EntityType.USER;
    }

    public static Date getExpirationDate(Claims claims) {
        return claims.getExpiration();
    }

    public static boolean isTokenExpired(Date expirationDate) {
        return expirationDate.before(new Date());
    }
}

