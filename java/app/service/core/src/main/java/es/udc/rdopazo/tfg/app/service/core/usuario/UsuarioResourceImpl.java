package es.udc.rdopazo.tfg.app.service.core.usuario;

import java.util.Calendar;
import java.util.List;

import javax.json.Json;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.usuario.UsuarioService;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.service.core.usuario.converter.UsuarioEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.usuario.updater.UsuarioEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.TokenServices;
import es.udc.rdopazo.tfg.service.api.usuario.UsuarioResource;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioDto;

@Service
public class UsuarioResourceImpl<U extends Usuario> implements UsuarioResource {

    @Autowired
    private UsuarioService<U> usuarioService;

    @Autowired
    private UsuarioEntityDtoConverter<UsuarioDto, U> converter;

    @Autowired
    private UsuarioEntityDtoUpdater<U> updater;

    @Autowired
    private TokenServices<U> tokenService;

    // CRUD
    public List<UsuarioDto> getAll() {

        return this.converter.toDtoList(this.usuarioService.getAll());
    }

    public UsuarioDto getById(String id) {
        U usuario = null;
        try {
            usuario = this.usuarioService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(usuario);
    }

    @Transactional
    public UsuarioDto create(UsuarioDto usuarioDto) {
        U usuario = this.converter.toEntity(usuarioDto);
        return this.converter.toDto(this.usuarioService.add(usuario));
    }

    @Transactional
    public UsuarioDto update(String id, UsuarioDto usuarioDto) {
        U usuario = null;
        try {
            usuario = this.usuarioService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        usuario = this.updater.update(usuarioDto, usuario);
        return this.converter.toDto(this.usuarioService.update(usuario));
    }

    @Transactional
    public void delete(String id) {
        try {
            this.usuarioService.delete(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    // END CRUD

    public Response authenticate(UsuarioDto usuarioDto) {

        String role = this.usuarioService.authenticate(usuarioDto.getUsername(), usuarioDto.getPassword());
        if (role != null) {
            return Response.ok(
                    Json.createObjectBuilder().add("token", this.createToken(usuarioDto.getUsername(), role)).build(),
                    MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private String createToken(String username, String role) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 3);
        return this.tokenService.createToken(username, role, cal.getTimeInMillis());
    }

    /*
     * public boolean authenticate(UsuarioDto usuarioDto) { boolean result = false; if ((usuarioDto.getNombre() != null)
     * && (usuarioDto.getPassword() != null)) { result = this.usuarioService.authenticate(usuarioDto.getNombre(),
     * usuarioDto.getPassword()); } return result; }
     */
}
