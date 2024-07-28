package com.bronya.manage.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final SecretKey secretKey = Jwts.SIG.HS256.key().build();
    private static final long expiration = 60 * 60_000; // expiration = 1h

    public static String getJwsString(Map<String, Object> claims) {
        return Jwts.builder() // get a JwtBuilder
                .header().keyId("bronya").and().claims(claims) // payload
                .signWith(secretKey) // sign
                .expiration(new Date(System.currentTimeMillis() + expiration)) // expiration = 1h
                .compact();
    }

    public static Claims parseJwsString(String jwsString) {
        return Jwts.parser() // get a JwtParserBuilder
                .verifyWith(secretKey).build() // get a thread-safe JwtParser
                .parseSignedClaims(jwsString).getPayload(); // parse jwsString
    }
}
