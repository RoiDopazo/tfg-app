package es.udc.rdopazo.tfg.app.model.core.usuario;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.UniqueConstraintException;

public interface UsuarioService<U extends Usuario> {

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.username == authentication" + ".principal.username")
    List<U> getAll(Integer index, Integer count);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.username == authentication" + ".principal.username")
    List<U> getByField(String field, Object value, Integer index, Integer count);

    @PreAuthorize("hasRole('ROLE_ADMIN') or @mySecurityService.hasUserPermission(authentication, #id)")
    void delete(Long id) throws InstanceNotFoundException;

    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.username == authentication" + ".principal.username")
    U getById(Long id) throws InstanceNotFoundException;

    U getByUsername(String username) throws InstanceNotFoundException;

    U add(U usuario) throws UniqueConstraintException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or #usuario.username == authentication.principal.username")
    U update(U usuario);

    U authenticate(String nombre, String pass);

    U validateRefreshToken(String refreshToken) throws InstanceNotFoundException;

}
