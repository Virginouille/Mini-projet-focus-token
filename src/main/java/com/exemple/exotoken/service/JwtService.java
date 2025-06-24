package com.exemple.exotoken.service;

import com.exemple.exotoken.config.SecurityConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {

    //injection SecurityConfig
    private final SecurityConfig securityConfig;

    //Expiration Time
    private static final int EXPIRATION_TIME = 1000 * 60;

    //Ajout de la clé signature du token
    private static final String SECRET_KEY = "dCGl4vh7XheffkTozKq7rrPrku+SnUL6dDtEZsAv5Tw=";

    //Generate token
    private String generateToken(String username) {
        return Jwts.builder()

                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                        .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Méthode qui confectionne la clé de signature
    private Key getSignedKey() {
       byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keyBytes);
    }
    //isValidToken
    //allClaims
    //PersonnalisableClaims

}
