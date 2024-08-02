package com.bronya.projdemo.utils;

import com.bronya.projdemo.dao.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletResponse;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

public class JwtUtil { // token: jwtString
    private static final SecretKey secretKey = Jwts.SIG.HS256.key().build();
    private static final long expiration = 60 * 60_000; // expiration = 1h

    public static String genJwtString(Map<String, Object> claims) {
        return Jwts.builder() // get a JwtBuilder
                .header().keyId("bronya").and().claims(claims) // payload
                .signWith(secretKey) // sign
                .expiration(new Date(System.currentTimeMillis() + expiration)) // expiration = 1h
                .compact();
    }

    public static Claims parseJwtString(String jwtString) {
        return Jwts.parser() // get a JwtParserBuilder
                .verifyWith(secretKey).build() // get a thread-safe JwtParser
                .parseSignedClaims(jwtString).getPayload(); // parse jwtString
    }

    public static void noTokenHandler(HttpServletResponse resp) throws IOException {
        resp.setStatus(401);
        resp.setContentType("application/json");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonString = ow.writeValueAsString(Result.err("Unauthorized Error"));
        PrintWriter writer = resp.getWriter();
        writer.write(jsonString);
    }
}
