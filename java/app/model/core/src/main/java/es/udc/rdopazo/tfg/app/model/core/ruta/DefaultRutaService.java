package es.udc.rdopazo.tfg.app.model.core.ruta;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.api.ruta.RutaService;
import es.udc.rdopazo.tfg.app.api.ruta.dto.RutaDto;
import es.udc.rdopazo.tfg.app.model.core.ruta.converter.RutaEntityDtoConverter;
import es.udc.rdopazo.tfg.app.model.core.ruta.updater.RutaEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.dao.RutaDao;

@Service
public class DefaultRutaService<D extends RutaDto, R extends Ruta<?>> implements RutaService<D> {

    @Autowired
    RutaDao<R> dao;

    @Autowired
    RutaEntityDtoConverter<D, R> converter;

    @Autowired
    RutaEntityDtoUpdater<R> updater;

    public List<D> getAll() {

        return this.converter.toDtoList(this.dao.getAll());
    }

    public D getById(String id) {
        R ruta = null;
        try {
            ruta = this.dao.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(ruta);
    }

    @Transactional
    public D create(RutaDto rutaDto) {

        @SuppressWarnings("unchecked")
        R ruta = this.converter.toEntity((D) rutaDto);
        this.dao.add(ruta);
        return this.converter.toDto(ruta);
    }

    @Transactional
    public D update(String id, RutaDto rutaDto) {
        R ruta = null;
        try {
            ruta = this.dao.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        ruta = this.updater.update(rutaDto, ruta);
        this.dao.update(ruta);
        return this.converter.toDto(ruta);
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
