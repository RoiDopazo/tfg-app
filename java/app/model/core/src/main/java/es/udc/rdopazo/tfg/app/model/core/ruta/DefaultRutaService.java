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
public class DefaultRutaService<R extends Ruta<?>> implements RutaService {

    @Autowired
    RutaDao<R> rutaDao;

    @Autowired
    RutaEntityDtoConverter<R> rutaConverter;

    @Autowired
    RutaEntityDtoUpdater<R> rutaUpdater;

    public List<RutaDto> getAll() {

        return this.rutaConverter.tuRutaDtoList(this.rutaDao.getAll());
    }

    public RutaDto getById(String id) {
        R ruta = null;
        try {
            ruta = this.rutaDao.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.rutaConverter.toRutaDto(ruta);
    }

    @Transactional
    public RutaDto create(RutaDto rutaDto) {

        R ruta = this.rutaConverter.toRutaEntity(rutaDto);
        this.rutaDao.add(ruta);
        return this.rutaConverter.toRutaDto(ruta);
    }

    @Transactional
    public RutaDto update(String id, RutaDto rutaDto) {
        R ruta = null;
        try {
            ruta = this.rutaDao.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        ruta = this.rutaUpdater.update(rutaDto, ruta);
        this.rutaDao.update(ruta);
        return this.rutaConverter.toRutaDto(ruta);
    }

    @Transactional
    public void delete(String id) {
        try {
            this.rutaDao.remove(this.rutaDao.getById(Long.parseLong(id)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
