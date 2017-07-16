package es.udc.rdopazo.tfg.app.model.core.usuario.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.usuario.dto.UsuarioDto;
import es.udc.rdopazo.tfg.app.model.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.usuario.JpaUsuario;

@Component
public class UsuarioEntityDtoConverter<D extends UsuarioDto, U extends Usuario>
        extends DefaultEntityDtoConverterSupport<D, U> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaUsuario.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return UsuarioDto.class;
    }

}
