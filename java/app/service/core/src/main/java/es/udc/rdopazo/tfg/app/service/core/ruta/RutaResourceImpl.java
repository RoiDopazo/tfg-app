package es.udc.rdopazo.tfg.app.service.core.ruta;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.ruta.RutaService;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.service.core.ruta.converter.RutaEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.ruta.updater.RutaEntityDtoUpdater;
import es.udc.rdopazo.tfg.service.api.ruta.RutaResource;
import es.udc.rdopazo.tfg.service.api.ruta.dto.RutaDto;

@Service
public class RutaResourceImpl<D extends Dia, R extends Ruta<D>> implements RutaResource {

    @Autowired
    RutaService<R> rutaService;

    @Autowired
    RutaEntityDtoConverter<D, RutaDto, R> converter;

    @Autowired
    RutaEntityDtoUpdater<R> updater;

    public List<RutaDto> getAll(String index, String count) {
        Integer indexInt = null;
        Integer countInt = null;

        try {
            indexInt = Integer.parseInt(index);
        } catch (NumberFormatException e) {
        }

        try {
            countInt = Integer.parseInt(count);
        } catch (NumberFormatException e) {
        }

        return this.converter.toDtoList(this.rutaService.getAll(indexInt, countInt));
    }

    public RutaDto getById(String id) {
        R ruta = null;
        try {
            ruta = this.rutaService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(ruta);
    }

    @Transactional
    public RutaDto create(RutaDto rutaDto) {
        R ruta = this.converter.toEntity(rutaDto);
        ruta.setNumDays(0);
        R r = this.rutaService.add(ruta);

        // D d = (D) new JpaDia();
        // d.setName("dia1");
        // D d2 = (D) new JpaDia();
        // d2.setName("dia2");
        //
        // ruta.addDay(d);
        // ruta.addDay(d2);
        // this.diaService.add(d);
        // this.diaService.add(d2);
        return this.converter.toDto(r);
    }

    @Transactional
    public RutaDto update(String id, RutaDto rutaDto) {
        R ruta = null;
        try {
            ruta = this.rutaService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        ruta = this.updater.update(rutaDto, ruta);
        return this.converter.toDto(this.rutaService.update(ruta));
    }

    @Transactional
    public void delete(String id) {
        try {
            this.rutaService.delete(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
