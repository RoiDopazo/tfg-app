package es.udc.rdopazo.tfg.app.model.core.usuario;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;

public interface UsuarioService<U extends Usuario> {

    List<U> getAll();

    U getById(Long id);

    U add(U usuario);

    U update(U usuario);

    void delete(Long id);

    boolean authenticate(String nombre, String pass);
}
