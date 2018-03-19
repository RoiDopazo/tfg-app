package es.udc.rdopazo.tfg.app.model.core.usuario;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public interface UsuarioService<U extends Usuario> {

    List<U> getAll(Integer index, Integer count);

    U getById(Long id) throws InstanceNotFoundException;

    List<U> getByField(String field, Object value);

    List<U> getByField(String field, Object value, Integer index, Integer count);

    U add(U usuario);

    U update(U usuario);

    void delete(Long id) throws InstanceNotFoundException;

    String authenticate(String nombre, String pass);

    boolean evaluateToken(String nombre, String token);

    String setToken(String nombre, String token);
}
