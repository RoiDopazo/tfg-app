package es.udc.rdopazo.tfg.app.service.core.comentario;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.comentario.ComentarioService;
import es.udc.rdopazo.tfg.app.model.persistence.api.comentario.Comentario;
import es.udc.rdopazo.tfg.app.service.core.comentario.converter.ComentarioEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.comentario.updater.ComentarioEntityDtoUpdater;
import es.udc.rdopazo.tfg.service.api.comentario.ComentarioResource;
import es.udc.rdopazo.tfg.service.api.comentario.dto.ComentarioDto;

@Service
public class ComentarioResourceImp<C extends Comentario> implements ComentarioResource {

    @Autowired
    ComentarioService<C> comentarioService;

    @Autowired
    ComentarioEntityDtoConverter<ComentarioDto, C> converter;

    @Autowired
    ComentarioEntityDtoUpdater<C> updater;

    public List<ComentarioDto> getAll() {

        return this.converter.toDtoList(this.comentarioService.getAll());
    }

    public ComentarioDto getById(String id) {
        C comentario = null;
        try {
            comentario = this.comentarioService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(comentario);
    }

    @Transactional
    public ComentarioDto create(ComentarioDto comentarioDto) {
        C comentario = this.converter.toEntity(comentarioDto);
        return this.converter.toDto(this.comentarioService.add(comentario));
    }

    @Transactional
    public ComentarioDto update(String id, ComentarioDto comentarioDto) {
        C comentario = null;
        try {
            comentario = this.comentarioService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        comentario = this.updater.update(comentarioDto, comentario);
        return this.converter.toDto(this.comentarioService.update(comentario));
    }

    @Transactional
    public void delete(String id) {
        try {
            this.comentarioService.delete(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
