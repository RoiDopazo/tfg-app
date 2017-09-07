package es.udc.rdopazo.tfg.app.service.core.ruta;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.dao.RutaDao;
import es.udc.rdopazo.tfg.app.service.core.ruta.converter.RutaEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.ruta.updater.RutaEntityDtoUpdater;
import es.udc.rdopazo.tfg.service.api.ruta.RutaService;
import es.udc.rdopazo.tfg.service.api.ruta.dto.RutaDto;

@Service
public class DefaultRutaService<R extends Ruta<?>> implements RutaService {

    @Autowired
    RutaDao<R> dao;

    @Autowired
    RutaEntityDtoConverter<RutaDto, R> converter;

    @Autowired
    RutaEntityDtoUpdater<R> updater;

    public List<RutaDto> getAll() {

        return this.converter.toDtoList(this.dao.getAll());
    }

    public RutaDto getById(String id) {
        R ruta = null;
        try {
            ruta = this.dao.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(ruta);
    }

    @Transactional
    public RutaDto create(RutaDto rutaDto) {
        R ruta = this.converter.toEntity(rutaDto);
        this.dao.add(ruta);
        return this.converter.toDto(ruta);
    }

    @Transactional
    public RutaDto update(String id, RutaDto rutaDto) {
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
