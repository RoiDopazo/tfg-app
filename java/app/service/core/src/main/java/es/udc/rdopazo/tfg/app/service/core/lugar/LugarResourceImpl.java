package es.udc.rdopazo.tfg.app.service.core.lugar;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.dialugar.DiaLugarService;
import es.udc.rdopazo.tfg.app.model.core.lugar.LugarService;
import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.DiaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.service.core.lugar.converter.LugarEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.lugar.updater.LugarEntityDtoUpdater;
import es.udc.rdopazo.tfg.service.api.lugar.LugarResource;
import es.udc.rdopazo.tfg.service.api.lugar.dto.LugarDto;

@Service
public class LugarResourceImpl<DL extends DiaLugar<?, L>, L extends Lugar> implements LugarResource {

    @Autowired
    LugarService<L> lugarService;

    @Autowired
    DiaLugarService<DL> diaLugarService;

    @Autowired
    LugarEntityDtoConverter<LugarDto, L> converter;

    @Autowired
    LugarEntityDtoUpdater<L> updater;

    public List<LugarDto> getAll() {

        return this.converter.toDtoList(this.lugarService.getAll());
    }

    public LugarDto getById(String id) {
        L lugar = null;
        try {
            lugar = this.lugarService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(lugar);
    }

    @Transactional
    public LugarDto create(LugarDto lugarDto) {
        L lugar = this.converter.toEntity(lugarDto);
        return this.converter.toDto(this.lugarService.add(lugar));
    }

    @Transactional
    public LugarDto update(String id, LugarDto lugarDto) {
        L lugar = null;
        try {
            lugar = this.lugarService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        lugar = this.updater.update(lugarDto, lugar);
        return this.converter.toDto(this.lugarService.update(lugar));
    }

    @Transactional
    public void delete(String id) {
        try {
            this.lugarService.delete(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public Boolean[] getListDaysBy(String route, String idFoursquare) {
        Long idRouteLong = null;
        try {
            idRouteLong = Long.parseLong(route);
        } catch (NumberFormatException e) {
        }
        return (this.diaLugarService.getListDaysByRotueAndPlace(idRouteLong, idFoursquare));
    }

}
