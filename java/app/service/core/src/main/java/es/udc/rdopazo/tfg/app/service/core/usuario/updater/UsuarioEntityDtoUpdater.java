package es.udc.rdopazo.tfg.app.service.core.usuario.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.Role;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioDto;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioPersistDto;

@Component
public class UsuarioEntityDtoUpdater<U extends Usuario> {

    public U update(UsuarioDto usuarioDto, U usuario) {
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setEmail(usuarioDto.getEmail());
        return usuario;
    }

    public U updatePersist(UsuarioPersistDto dto, U usuario) {
        usuario.setCreationDate(dto.getCreationDate());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setRole(Role.valueOf(dto.getRole()));
        usuario.setUsername(dto.getUsername());
        return usuario;
    }
}
