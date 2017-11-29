package es.udc.rdopazo.tfg.app.service.core.stay.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayDto;

@Component
public class StayEntityDtoUpdater<S extends Stay<?, ?, ?>> {

    public S update(StayDto stayDto, S stay) {
        stay.setOrder(stayDto.getOrder());
        stay.setTime(stayDto.getTime());
        stay.setTravelDistance(stayDto.getTravelDistance());
        stay.setTravelMode(stayDto.getTravelMode());
        stay.setTravelTime(stayDto.getTravelTime());
        return stay;
    }
}
