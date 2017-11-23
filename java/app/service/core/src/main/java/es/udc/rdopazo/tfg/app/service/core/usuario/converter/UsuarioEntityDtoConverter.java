package es.udc.rdopazo.tfg.app.service.core.usuario.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.user.JpaUser;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioDto;

@Component
public class UsuarioEntityDtoConverter<D extends UsuarioDto, U extends Usuario>
        extends DefaultEntityDtoConverterSupport<D, U> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaUser.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return UsuarioDto.class;
    }

}
