package es.udc.rdopazo.tfg.app.service.core.usuario.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioDto;

@Component
public class UsuarioEntityDtoUpdater<U extends Usuario> {

    public U update(UsuarioDto usuarioDto, U usuario) {
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setEmail(usuarioDto.getEmail());
        return usuario;
    }
}
