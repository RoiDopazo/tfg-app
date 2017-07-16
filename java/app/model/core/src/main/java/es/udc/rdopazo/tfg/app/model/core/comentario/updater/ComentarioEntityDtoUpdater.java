package es.udc.rdopazo.tfg.app.model.core.comentario.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.comentario.dto.ComentarioDto;
import es.udc.rdopazo.tfg.app.model.persistence.api.comentario.Comentario;

@Component
public class ComentarioEntityDtoUpdater<C extends Comentario> {

    public C update(ComentarioDto comentarioDto, C comentario) {
        comentario.setCuerpo(comentarioDto.getCuerpo());
        comentario.setEstado(comentarioDto.getEstado());
        comentario.setRazon(comentarioDto.getRazon());
        comentario.setTitulo(comentarioDto.getTitulo());
        return comentario;
    }
}
