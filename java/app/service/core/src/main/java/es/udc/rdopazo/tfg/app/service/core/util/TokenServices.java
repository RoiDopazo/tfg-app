package es.udc.rdopazo.tfg.app.service.core.util;

import java.security.Key;
import java.security.SignatureException;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

public class TokenServices {

    private static final Key signingKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary("MyKey"),
            SignatureAlgorithm.HS512.getJcaName());

    public static String createToken(String username, String role, long expired) {
        return Jwts.builder().setSubject(username).claim("role", role).signWith(SignatureAlgorithm.HS512, signingKey)
                .setExpiration(new Date(expired)).compact();
    }

    public static String validateToken(String token) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException {
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        String role = null;
        role = claims.get("role").toString();
        Jwts.parser().requireSubject(claims.getSubject()).setSigningKey(signingKey).parseClaimsJws(token);

        return role;
    }

}
