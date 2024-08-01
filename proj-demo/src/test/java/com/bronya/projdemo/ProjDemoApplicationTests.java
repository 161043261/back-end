package com.bronya.projdemo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
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
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "James Gosling");

        String jwtString = Jwts.builder() // get a JwtBuilder
                .header().keyId("bronya").and().claims(claims) // payload
                .signWith(secretKey) // signature
                .expiration(new Date(System.currentTimeMillis() + 60_000)) // expiration
                .compact();

        System.out.println(jwtString);

        // parse jwtString
        Claims claimsMap = Jwts.parser() // get a JwtParserBuilder
                .verifyWith(decoded).build() // get a thread-safe JwtParser
                .parseSignedClaims(jwtString).getPayload(); // parse jwtString
        System.out.println(claimsMap);
    }

    @Test
    public void testThreadLocal() throws InterruptedException {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        new Thread(() -> {
            threadLocal.set(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
            log.info("threadA: K={} V={}", Thread.currentThread().getName(), threadLocal.get());
        }, "threadA").start();
        Thread.sleep(5000);
        new Thread(() -> {
            threadLocal.set(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
            log.info("threadB: K={} V={}", Thread.currentThread().getName(), threadLocal.get());
        }, "threadB").start();
    }
}
