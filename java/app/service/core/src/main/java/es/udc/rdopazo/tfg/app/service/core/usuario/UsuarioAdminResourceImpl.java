package es.udc.rdopazo.tfg.app.service.core.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.usuario.UsuarioService;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.service.core.usuario.converter.UsuarioPersistEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.usuario.updater.UsuarioEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.usuario.UsuarioAdminResource;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioPersistDto;

@Service
public class UsuarioAdminResourceImpl<U extends Usuario> implements UsuarioAdminResource {

    @Autowired
    UsuarioService<U> service;

    @Autowired
    UsuarioPersistEntityDtoConverter<U, UsuarioPersistDto> converter;

    @Autowired
    private UsuarioEntityDtoUpdater<U> updater;

    public List<UsuarioPersistDto> getAll(String filter, String value, String index, String count)
            throws InputValidationException {

        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);

        List<UsuarioPersistDto> result = null;
        if (!(filter.equals("null")) && !(value.equals("null"))) {
            result = this.converter.toDtoList(this.service.getByField(filter, value, indexInt, countInt));
        } else {
            result = this.converter.toDtoList(this.service.getAll(indexInt, countInt));
        }
        return result;
    }

    public UsuarioPersistDto getById(String id) throws InstanceNotFoundException, InputValidationException {
        Long idUser = InputValidator.validateLongNull("idUser", id);
        return this.converter.toDto(this.service.getById(idUser));
    }

    public UsuarioPersistDto create(UsuarioPersistDto usuarioPersistDto) throws InputValidationException {
        InputValidator.validateEnumRoleUser("role", usuarioPersistDto.getRole());
        U user = this.converter.toEntity(usuarioPersistDto);
        user = this.service.add(user);
        return this.converter.toDto(user);
    }

    public UsuarioPersistDto update(String id, UsuarioPersistDto usuarioPersistDto)
            throws InstanceNotFoundException, InputValidationException {
        InputValidator.validateEnumRoleUser("role", usuarioPersistDto.getRole());
        Long idUser = InputValidator.validateLongNull("idUser", id);
        U user = this.service.getById(idUser);
        user = this.updater.updatePersist(usuarioPersistDto, user);
        return this.converter.toDto(this.service.update(user));
    }

    public void delete(String id) throws InstanceNotFoundException, InputValidationException {
        Long idUser = InputValidator.validateLongNull("idUser", id);
        this.service.delete(idUser);
    }

}
