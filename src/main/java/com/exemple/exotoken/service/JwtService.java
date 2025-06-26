package com.exemple.exotoken.service;

import com.exemple.exotoken.config.SecurityConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class JwtService {

    //injection SecurityConfig
    private final SecurityConfig securityConfig;

    //Expiration Time
    private static final int EXPIRATION_TIME = 1000 * 60; //Expiration temporaire pour tests

    //Ajout de la clé signature du token
    private static final String SECRET_KEY = "dCGl4vh7XheffkTozKq7rrPrku+SnUL6dDtEZsAv5Tw=";

    //Generate token
    public String generateToken(String username) {
        return Jwts.builder()
                //Le header est généré par défaut pas jjwt
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                        .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**Méthode qui confectionne la clé de signature*/
    private Key getSignedKey() {
       byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keyBytes);
    }

    /**Méthode pour extraire tous les claims d'un token*/
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**Méthode générique pour extraire uniquement un type de claims*
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**Méthode pour extraire le claims username du token*/
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**Méthode pour extraire uniquement le claim date expiration*/
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**Méthode qui vérifie si le claims est valide*/
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    /**Méthode qui vérifie la validité du token*/ //Améliorer avec une validation plus poussée via username
    public boolean isTokenValid(String token) {
        if (token == null) {
            return false;
        }

        try {
            return !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }

    }

}
