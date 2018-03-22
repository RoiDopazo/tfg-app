package es.udc.rdopazo.tfg.app.service.core.usuario;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.ForbiddenException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.usuario.UsuarioService;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.service.core.usuario.converter.UsuarioEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.usuario.updater.UsuarioEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.TokenServices;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.usuario.UsuarioResource;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioDto;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

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

        return this.converter.toDtoList(this.usuarioService.getAll(null, null));
    }

    public UsuarioDto getById(String id) throws InstanceNotFoundException {
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
    public UsuarioDto update(String id, UsuarioDto usuarioDto) throws InstanceNotFoundException {
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
    public void delete(String id) throws InstanceNotFoundException {
        Long idLong = null;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {

        }
        this.usuarioService.delete(idLong);
    }
    // END CRUD

    public TokenDto authenticate(UsuarioDto usuarioDto) throws ForbiddenException {

        U user = this.usuarioService.authenticate(usuarioDto.getUsername(), usuarioDto.getPassword());
        if (user.getRole().toString() != null) {
            TokenDto token = new TokenDto();
            token.setToken(this.createJwtToken(usuarioDto.getUsername(), user.getRole().toString()));
            token.setRefreshToken(user.getToken());
            token.setRole(user.getRole().toString());
            token.setName(usuarioDto.getUsername());
            return token;
        } else {
            throw new ForbiddenException();
        }
    }

    private String createJwtToken(String username, String role) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 1);
        return this.tokenService.createToken(username, role, cal.getTimeInMillis());
    }

    public TokenDto refreshToken(String refreshToken) throws ForbiddenException {
        U user = this.usuarioService.validateRefreshToken(refreshToken);
        TokenDto token = new TokenDto();
        token.setToken(this.createJwtToken(user.getUsername(), user.getRole().toString()));
        token.setRefreshToken(user.getToken());
        token.setRole(user.getRole().toString());
        token.setName(user.getUsername());
        return token;
    }

}
