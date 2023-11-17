package mariangelamarasciuolo.ProgettoSettimanales7d5.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import mariangelamarasciuolo.ProgettoSettimanales7d5.entities.Utente;
import mariangelamarasciuolo.ProgettoSettimanales7d5.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {
    @Value("${SJS}")
    private String secret;

    public String createToken(Utente utente) {

        return Jwts.builder().setSubject(String.valueOf(utente.getId()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();

    }

    public void verifyToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Il token non è valido! Effettua nuovamente il login!");
        }

    }

    public String extractIdFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build().parseClaimsJws(token).getBody().getSubject();

    }
}
