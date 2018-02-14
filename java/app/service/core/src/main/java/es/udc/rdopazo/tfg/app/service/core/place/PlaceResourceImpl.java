package es.udc.rdopazo.tfg.app.service.core.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.place.PlaceService;
import es.udc.rdopazo.tfg.app.model.core.stay.StayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.service.core.place.converter.PlaceEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.place.updater.PlaceEntityDtoUpdater;
import es.udc.rdopazo.tfg.service.api.place.PlaceResource;
import es.udc.rdopazo.tfg.service.api.place.dto.PlaceDto;

@Service
public class PlaceResourceImpl<S extends Stay<?, ?, ?>, L extends Place> implements PlaceResource {

    @Autowired
    PlaceService<L> lugarService;

    @Autowired
    StayService<S> stayService;

    @Autowired
    PlaceEntityDtoConverter<PlaceDto, L> converter;

    @Autowired
    PlaceEntityDtoUpdater<L> updater;

    public List<PlaceDto> getAll() {

        return this.converter.toDtoList(this.lugarService.getAll());
    }

    public PlaceDto getById(String id) {
        L lugar = null;
        try {
            lugar = this.lugarService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(lugar);
    }

    public PlaceDto create(PlaceDto lugarDto) {
        L lugar = this.converter.toEntity(lugarDto);
        return this.converter.toDto(this.lugarService.add(lugar));
    }

    public PlaceDto update(String id, PlaceDto lugarDto) {
        L lugar = null;
        try {
            lugar = this.lugarService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        lugar = this.updater.update(lugarDto, lugar);
        return this.converter.toDto(this.lugarService.update(lugar));
    }

    public void delete(String id) {
        Long idLong = null;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {

        }
        this.lugarService.delete(idLong);
    }

}
