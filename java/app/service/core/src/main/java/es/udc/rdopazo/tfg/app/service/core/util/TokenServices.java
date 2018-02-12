package es.udc.rdopazo.tfg.app.service.core.util;

import java.security.Key;
import java.security.SignatureException;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.core.usuario.UsuarioService;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenServices<U extends Usuario> {

    @Autowired
    UsuarioService<U> userService;

    private static final Key signingKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary("MyKey"),
            SignatureAlgorithm.HS512.getJcaName());

    public String createToken(String username, String role, long expired) {
        return Jwts.builder().setSubject(username).claim("role", role).signWith(SignatureAlgorithm.HS512, signingKey)
                .setExpiration(new Date(expired)).compact();
    }

    public String validateToken(String token) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException {
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        String role = null;
        role = claims.get("role").toString();
        Jwts.parser().requireSubject(claims.getSubject()).setSigningKey(signingKey).parseClaimsJws(token);
        return role;
    }

    public Usuario getUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        List<U> user = this.userService.getByField("username", claims.getSubject().toLowerCase());
        Jwts.parser().requireSubject(claims.getSubject()).setSigningKey(signingKey).parseClaimsJws(token);

        if (user.size() > 0) {
            return user.get(0);
        } else {
            return null;
        }
    }

}
