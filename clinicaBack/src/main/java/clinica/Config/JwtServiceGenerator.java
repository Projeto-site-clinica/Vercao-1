package clinica.Config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import clinica.Entity.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class JwtServiceGenerator {

    public String generateToken(Usuario userDetails) {

        //AQUI VOCÊ PODE COLOCAR O QUE MAIS VAI COMPOR O PAYLOAD DO TOKEN
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", userDetails.getUsername());
        extraClaims.put("id", userDetails.getId().toString());
        extraClaims.put("role", userDetails.getRole());
        extraClaims.put("outracoisa", "teste");

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(new Date().getTime() + 3600000 * JwtParameters.HORAS_EXPIRACAO_TOKEN))
                .signWith(getSigningKey(), JwtParameters.ALGORITMO_ASSINATURA)
                .compact();
    }

//    public String generateTokenPaciente(Paciente userDetails) {
//
//        //AQUI VOCÊ PODE COLOCAR O QUE MAIS VAI COMPOR O PAYLOAD DO TOKEN
//        Map<String, Object> extraClaims = new HashMap<>();
//        extraClaims.put("username", userDetails.getUsername());
//        extraClaims.put("id", userDetails.getId().toString());
//        extraClaims.put("role", userDetails.getRole());
//        extraClaims.put("outracoisa", "teste");
//
//        return Jwts
//                .builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(new Date().getTime() + 3600000 * JwtParameters.HORAS_EXPIRACAO_TOKEN))
//                .signWith(getSigningKey(), JwtParameters.ALGORITMO_ASSINATURA)
//                .compact();
//    }
//
//    public String generateTokenClinica(Clinica userDetails) {
//
//        //AQUI VOCÊ PODE COLOCAR O QUE MAIS VAI COMPOR O PAYLOAD DO TOKEN
//        Map<String, Object> extraClaims = new HashMap<>();
//        extraClaims.put("username", userDetails.getUsername());
//        extraClaims.put("id", userDetails.getId().toString());
//        extraClaims.put("role", userDetails.getRole());
//        extraClaims.put("outracoisa", "teste");
//
//        return Jwts
//                .builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(new Date().getTime() + 3600000 * JwtParameters.HORAS_EXPIRACAO_TOKEN))
//                .signWith(getSigningKey(), JwtParameters.ALGORITMO_ASSINATURA)
//                .compact();
//    }
//
//    public String generateTokenDoutor(Doutor userDetails) {
//
//        //AQUI VOCÊ PODE COLOCAR O QUE MAIS VAI COMPOR O PAYLOAD DO TOKEN
//        Map<String, Object> extraClaims = new HashMap<>();
//        extraClaims.put("username", userDetails.getUsername());
//        extraClaims.put("id", userDetails.getId().toString());
//        extraClaims.put("role", userDetails.getRole());
//        extraClaims.put("outracoisa", "teste");
//
//        return Jwts
//                .builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(new Date().getTime() + 3600000 * JwtParameters.HORAS_EXPIRACAO_TOKEN))
//                .signWith(getSigningKey(), JwtParameters.ALGORITMO_ASSINATURA)
//                .compact();
//    }
//
//    public String generateTokenSecretaria(Secretaria userDetails) {
//
//        //AQUI VOCÊ PODE COLOCAR O QUE MAIS VAI COMPOR O PAYLOAD DO TOKEN
//        Map<String, Object> extraClaims = new HashMap<>();
//        extraClaims.put("username", userDetails.getUsername());
//        extraClaims.put("id", userDetails.getId().toString());
//        extraClaims.put("role", userDetails.getRole());
//        extraClaims.put("outracoisa", "teste");
//
//        return Jwts
//                .builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(new Date().getTime() + 3600000 * JwtParameters.HORAS_EXPIRACAO_TOKEN))
//                .signWith(getSigningKey(), JwtParameters.ALGORITMO_ASSINATURA)
//                .compact();
//    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JwtParameters.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

}