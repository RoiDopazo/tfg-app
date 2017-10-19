package es.udc.rdopazo.tfg.app.service.core.dialugar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.dia.DiaService;
import es.udc.rdopazo.tfg.app.model.core.dialugar.DiaLugarService;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.DiaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.service.core.dialugar.converter.DiaLugarEntityDtoConverter;
import es.udc.rdopazo.tfg.service.api.dialugar.DiaLugarResource;
import es.udc.rdopazo.tfg.service.api.dialugar.dto.DiaLugarDto;

@Service
public class DiaLugarResourceImpl<L extends Lugar, R extends Ruta<D>, D extends Dia<DL>, DL extends DiaLugar<D, L>>
        implements DiaLugarResource {

    @Autowired
    private DiaLugarService<DL> diaLugarService;

    @Autowired
    private DiaLugarEntityDtoConverter<DiaLugarDto, DL> converter;

    @Autowired
    private DiaService<R, D> diaService;

    public List<DiaLugarDto> getAll(String idRoute, String idDay, String index, String count) {

        Long idRouteLong = null;
        Long idDayLong = null;

        try {
            idDayLong = Long.parseLong(idDay);
        } catch (NumberFormatException e) {
        }

        try {
            idRouteLong = Long.parseLong(idRoute);
        } catch (NumberFormatException e) {
        }

        return this.converter.toDtoList(this.diaLugarService.getAllInDay(idRouteLong, idDayLong));
    }

    public DiaLugarDto create(String idRoute, String idDay, DiaLugarDto persistDayPlace) {

        Long idRouteLong = null;
        Long idDayLong = null;

        try {
            idDayLong = Long.parseLong(idDay);
        } catch (NumberFormatException e) {
        }

        try {
            idRouteLong = Long.parseLong(idRoute);
        } catch (NumberFormatException e) {
        }

        D day = this.diaService.getById(idRouteLong, idDayLong);
        DL entity = this.converter.toEntity(persistDayPlace);
        entity.setDay(day);
        return this.converter.toDto(this.diaLugarService.add(entity));
    }

    public void delete(String idRoute, String idDay, String id) {
        Long idLong = null;

        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
        }

        this.diaLugarService.delete(idLong);

    }

}
