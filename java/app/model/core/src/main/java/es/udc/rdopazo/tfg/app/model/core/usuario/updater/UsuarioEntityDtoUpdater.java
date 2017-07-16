package es.udc.rdopazo.tfg.app.model.core.usuario.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.usuario.dto.UsuarioDto;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;

@Component
public class UsuarioEntityDtoUpdater<U extends Usuario> {

    public U update(UsuarioDto usuarioDto, U usuario) {
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setEmail(usuarioDto.getEmail());
        return usuario;
    }
}
