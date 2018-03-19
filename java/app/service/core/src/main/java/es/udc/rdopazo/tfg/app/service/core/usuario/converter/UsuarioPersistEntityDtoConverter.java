package es.udc.rdopazo.tfg.app.service.core.usuario.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.user.JpaUser;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioPersistDto;

@Component
public class UsuarioPersistEntityDtoConverter<U extends Usuario, DT extends UsuarioPersistDto>
        extends DefaultEntityDtoConverterSupport<DT, U> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaUser.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return UsuarioPersistDto.class;
    }

}
