package es.udc.rdopazo.tfg.app.model.core.usuario;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.api.usuario.UsuarioService;
import es.udc.rdopazo.tfg.app.api.usuario.dto.UsuarioDto;
import es.udc.rdopazo.tfg.app.model.core.usuario.converter.UsuarioEntityDtoConverter;
import es.udc.rdopazo.tfg.app.model.core.usuario.updater.UsuarioEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.dao.UsuarioDao;

@Service
public class DefaultUsuarioService<U extends Usuario> implements UsuarioService {

    @Autowired
    UsuarioDao<U> dao;

    @Autowired
    UsuarioEntityDtoConverter<UsuarioDto, U> converter;

    @Autowired
    UsuarioEntityDtoUpdater<U> updater;

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

}
