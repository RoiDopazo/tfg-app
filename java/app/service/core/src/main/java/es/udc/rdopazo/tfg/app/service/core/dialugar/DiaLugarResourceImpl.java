package es.udc.rdopazo.tfg.app.service.core.dialugar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.dia.DiaService;
import es.udc.rdopazo.tfg.app.model.core.dialugar.DiaLugarService;
import es.udc.rdopazo.tfg.app.model.core.lugar.LugarService;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.DiaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.service.core.dialugar.converter.DiaLugarEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.dialugar.updater.DiaLugarEntityDtoUpdater;
import es.udc.rdopazo.tfg.service.api.dialugar.DiaLugarResource;
import es.udc.rdopazo.tfg.service.api.dialugar.dto.DiaLugarConfListDto;
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

    @Autowired
    private DiaLugarEntityDtoUpdater<DL> updater;

    @Autowired
    LugarService<L> lugarService;

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

    public List<DiaLugarDto> update(String idRoute, String idDay, List<DiaLugarDto> dayPlaceDtoList) {

        List<DiaLugarDto> returnList = new ArrayList<DiaLugarDto>();
        for (DiaLugarDto dayPlaceDto : dayPlaceDtoList) {
            DL dayPlace = this.diaLugarService.getById(dayPlaceDto.getId());
            dayPlace = this.updater.update(dayPlaceDto, dayPlace);
            returnList.add(this.converter.toDto(this.diaLugarService.update(dayPlace)));
        }
        return returnList;
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
        entity.setOrder(this.diaLugarService.getMaxOrderNum(idRouteLong, idDayLong));
        L place = this.lugarService.getByField("idFoursquare", entity.getPlace().getIdFoursquare());
        if (place != null) {
            entity.getPlace().setId(place.getId());
        } else {
            L p = this.lugarService.add(entity.getPlace());
            entity.setPlace(p);
        }
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

    public Boolean batchCreateDelete(String idRoute, DiaLugarConfListDto diaLugarConfDto) {

        Long idRouteLong = null;
        Long idDayLong = null;

        try {
            idRouteLong = Long.parseLong(idRoute);
        } catch (NumberFormatException e) {
        }

        Set<Long> intersect = new HashSet<Long>(diaLugarConfDto.getDaysBefore());
        intersect.retainAll(diaLugarConfDto.getDaysAfter());
        diaLugarConfDto.getDaysBefore().removeAll(intersect);
        diaLugarConfDto.getDaysAfter().removeAll(intersect);

        // Lugares a eliminar del día
        for (Long idDay : diaLugarConfDto.getDaysBefore()) {
            L place = this.lugarService.getByField("idFoursquare",
                    diaLugarConfDto.getDiaLugar().getPlace().getIdFoursquare());
            if (place != null) {
                List<DL> dialugar = this.diaLugarService.getByRouteAndDayAndPlace(idRouteLong, idDay, place.getId());
                for (DL d : dialugar) {
                    this.delete(idRoute, idDay.toString(), d.getId().toString());
                }
            }
            this.diaLugarService.fixOrdersAfterDelete(idRouteLong, idDay);
        }

        // Lugares a añadir en el dia
        for (Long idDay : diaLugarConfDto.getDaysAfter()) {
            this.create(idRoute, idDay.toString(), diaLugarConfDto.getDiaLugar());
        }

        System.out.println(intersect);
        System.out.println(diaLugarConfDto.getDaysBefore());
        System.out.println(diaLugarConfDto.getDaysAfter());
        return true;

    }

}
