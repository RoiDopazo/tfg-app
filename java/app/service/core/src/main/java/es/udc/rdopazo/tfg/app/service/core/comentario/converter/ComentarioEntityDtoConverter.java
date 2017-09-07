package es.udc.rdopazo.tfg.app.service.core.comentario.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.comentario.Comentario;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.comentario.JpaComentario;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.comentario.dto.ComentarioDto;

@Component
public class ComentarioEntityDtoConverter<D extends ComentarioDto, C extends Comentario>
        extends DefaultEntityDtoConverterSupport<D, C> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaComentario.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return ComentarioDto.class;
    }
}
