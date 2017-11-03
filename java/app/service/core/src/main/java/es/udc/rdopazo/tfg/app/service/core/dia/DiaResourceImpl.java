package es.udc.rdopazo.tfg.app.service.core.dia;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.dia.DiaService;
import es.udc.rdopazo.tfg.app.model.core.ruta.RutaService;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.service.core.dia.converter.DiaEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.dia.converter.DiaPersistDtoConverter;
import es.udc.rdopazo.tfg.service.api.dia.DiaResource;
import es.udc.rdopazo.tfg.service.api.dia.dto.DiaDto;
import es.udc.rdopazo.tfg.service.api.dia.dto.DiaPersistDto;

@Service
public class DiaResourceImpl<R extends Ruta<D>, D extends Dia> implements DiaResource {

    @Autowired
    RutaService<R> rutaService;

    @Autowired
    DiaService<R, D> diaService;

    @Autowired
    DiaEntityDtoConverter<DiaDto, D> converter;

    @Autowired
    DiaPersistDtoConverter<DiaPersistDto, D> persistConverter;

    public List<DiaDto> getAll(String idRoute, String index, String count) {

        Integer indexInt = null;
        Integer countInt = null;
        Long idRouteLong = null;

        try {
            indexInt = Integer.parseInt(index);
        } catch (NumberFormatException e) {
        }

        try {
            countInt = Integer.parseInt(count);
        } catch (NumberFormatException e) {
        }

        try {
            idRouteLong = Long.parseLong(idRoute);
        } catch (NumberFormatException e) {
        }

        return this.converter.toDtoList(this.diaService.getAll(idRouteLong, indexInt, countInt));
    }

    @Transactional
    public DiaDto create(String idRoute, DiaPersistDto dayPersist) {
        R route = null;
        try {
            route = this.rutaService.getById(Long.parseLong(idRoute));
        } catch (NumberFormatException e) {

        }
        return this.converter.toDto(this.diaService.add(route, this.persistConverter.toEntity(dayPersist)));
    }

    @Transactional
    public List<DiaDto> createNumDays(String idRoute, Integer numDays) {
        R route = null;
        try {
            route = this.rutaService.getById(Long.parseLong(idRoute));
        } catch (NumberFormatException e) {

        }
        return this.converter.toDtoList(this.diaService.createDays(route, numDays));
    }

    @Transactional
    public void delete(String idRoute) {
        R route = null;
        Long routeId = null;
        try {
            routeId = Long.parseLong(idRoute);
            route = this.rutaService.getById(routeId);
        } catch (NumberFormatException e) {

        }
        if (route != null) {
            this.diaService.delete(routeId, (long) (route.getDays().size()));
        }

    }

    public DiaDto calculateHours(String idRoute, DiaDto diaDto) {
        // FIX -> hacer un validate de la ruta

        System.out.println(diaDto.getStartTime());
        for (int i = 0; i < diaDto.getPlaces().size(); i++) {
            if (i != 0) {
                diaDto.getPlaces().get(i - 1).getTime();
            }

        }

        return null;
    }

    public DiaDto update(String idRoute, String idDay, DiaDto diaDto) {
        // TODO Auto-generated method stub
        return null;
    }

}
