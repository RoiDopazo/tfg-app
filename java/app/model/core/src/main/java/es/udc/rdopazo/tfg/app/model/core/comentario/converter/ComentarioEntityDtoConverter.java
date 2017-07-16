package es.udc.rdopazo.tfg.app.model.core.comentario.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.comentario.dto.ComentarioDto;
import es.udc.rdopazo.tfg.app.model.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.app.model.persistence.api.comentario.Comentario;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.comentario.JpaComentario;

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
