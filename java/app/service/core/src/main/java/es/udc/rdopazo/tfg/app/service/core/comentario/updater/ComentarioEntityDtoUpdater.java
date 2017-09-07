package es.udc.rdopazo.tfg.app.service.core.comentario.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.comentario.Comentario;
import es.udc.rdopazo.tfg.service.api.comentario.dto.ComentarioDto;

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
