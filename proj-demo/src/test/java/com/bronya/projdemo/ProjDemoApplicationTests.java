package com.bronya.projdemo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ProjDemoApplicationTests {

    @Test
    public void testJwt() {
        // generate secretKey
        SecretKey secretKey = Jwts.SIG.HS256.key().build();
        // base64 encode
        String encoded = Encoders.BASE64.encode(secretKey.getEncoded());
        // base64 decode
        SecretKey decoded = Keys.hmacShaKeyFor(Decoders.BASE64.decode(encoded));
        System.out.println(secretKey.equals(decoded));

        // generate payload
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", 1);
        claimsMap.put("name", "James Gosling");

        String jwtString = Jwts.builder() // get a JwtBuilder
                .header().keyId("bronya").and().claims(claimsMap) // payload
                .signWith(secretKey) // signature
                .expiration(new Date(System.currentTimeMillis() + 60_000)) // expiration
                .compact();

        System.out.println(jwtString);

        // parse jwtString
        Claims claims = Jwts.parser() // get a JwtParserBuilder
                .verifyWith(decoded).build() // get a thread-safe JwtParser
                .parseSignedClaims(jwtString).getPayload(); // parse jwtString
        System.out.println(claims);
    }

    @Test
    public void testThreadLocal() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        new Thread(() -> {
            threadLocal.set("ValueA");
            System.out.println("K = " + Thread.currentThread().getName() + ", V = " + threadLocal.get());
        }, "ThreadA").start(); // K = ThreadA, V = ValueA
        new Thread(() -> {
            threadLocal.set("ValueB");
            System.out.println("K = " + Thread.currentThread().getName() + ", V = " + threadLocal.get());
        }, "ThreadB").start(); // K = ThreadB, V = ValueB
    }
}
