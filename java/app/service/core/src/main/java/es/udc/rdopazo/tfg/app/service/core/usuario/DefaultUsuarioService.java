package es.udc.rdopazo.tfg.app.service.core.usuario;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.dao.UsuarioDao;
import es.udc.rdopazo.tfg.app.service.core.usuario.converter.UsuarioEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.usuario.updater.UsuarioEntityDtoUpdater;
import es.udc.rdopazo.tfg.service.api.usuario.UsuarioService;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioDto;

@Service
public class DefaultUsuarioService<U extends Usuario> implements UsuarioService {

    @Autowired
    UsuarioDao<U> dao;

    @Autowired
    UsuarioEntityDtoConverter<UsuarioDto, U> converter;

    @Autowired
    UsuarioEntityDtoUpdater<U> updater;

    // CRUD
    public List<UsuarioDto> getAll() {

        return this.converter.toDtoList(this.dao.getAll());
    }

    public UsuarioDto getById(String id) {
        U usuario = null;
        try {
            usuario = this.dao.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(usuario);
    }

    @Transactional
    public UsuarioDto create(UsuarioDto usuarioDto) {
        U usuario = this.converter.toEntity(usuarioDto);
        this.dao.add(usuario);
        return this.converter.toDto(usuario);
    }

    @Transactional
    public UsuarioDto update(String id, UsuarioDto usuarioDto) {
        U usuario = null;
        try {
            usuario = this.dao.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        usuario = this.updater.update(usuarioDto, usuario);
        this.dao.update(usuario);
        return this.converter.toDto(usuario);
    }

    @Transactional
    public void delete(String id) {
        try {
            this.dao.remove(this.dao.getById(Long.parseLong(id)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    // END CRUD

    public boolean authenticate(UsuarioDto usuarioDto) {
        if ((usuarioDto.getNombre() != null) && (usuarioDto.getPassword() != null)) {
            List<U> usuario = this.dao.getListByField("nombre", usuarioDto.getNombre());
            System.out.println(usuario.get(0).getNombre());
            if ((!usuario.isEmpty()) && (usuario.get(0).getPassword().equals(usuarioDto.getPassword()))) {
                return true;
            }
        }
        return false;
    }
}
