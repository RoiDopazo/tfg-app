package es.udc.rdopazo.tfg.app.service.core.dialugar.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.DiaLugar;
import es.udc.rdopazo.tfg.service.api.dialugar.dto.DiaLugarDto;

@Component
public class DiaLugarEntityDtoUpdater<DL extends DiaLugar<?, ?>> {

    public DL update(DiaLugarDto dayPlaceDto, DL dayPlace) {
        dayPlace.setOrder(dayPlaceDto.getOrder());
        dayPlace.setTime(dayPlaceDto.getTime());
        dayPlace.setTravelDistance(dayPlaceDto.getTravelDistance());
        dayPlace.setTravelMode(dayPlaceDto.getTravelMode());
        dayPlace.setTravelTime(dayPlaceDto.getTravelTime());
        return dayPlace;
    }
}
