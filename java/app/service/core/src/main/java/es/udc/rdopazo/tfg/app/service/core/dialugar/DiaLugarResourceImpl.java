package es.udc.rdopazo.tfg.app.service.core.dialugar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.dialugar.DiaLugarService;
import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.DiaLugar;
import es.udc.rdopazo.tfg.app.service.core.dialugar.converter.DiaLugarEntityDtoConverter;
import es.udc.rdopazo.tfg.service.api.dia.dto.DiaDto;
import es.udc.rdopazo.tfg.service.api.dialugar.DiaLugarResource;
import es.udc.rdopazo.tfg.service.api.dialugar.dto.DiaLugarDto;

@Service
public class DiaLugarResourceImpl<DL extends DiaLugar<?>> implements DiaLugarResource {

    @Autowired
    private DiaLugarService<DL> diaLugarService;

    @Autowired
    private DiaLugarEntityDtoConverter<DiaLugarDto, DL> converter;

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

    public DiaDto create(String idRoute, String idDay, DiaLugarDto persistDayPlace) {
        // TODO Auto-generated method stub
        return null;
    }

    public void delete(String idRoute, String idDay, String id) {
        // TODO Auto-generated method stub

    }

}
