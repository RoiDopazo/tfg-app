package es.udc.rdopazo.tfg.app.service.core.place.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.service.api.place.dto.PlacePersistDto;

@Component
public class PlaceEntityDtoUpdater<L extends Place> {

    public L updatePersist(PlacePersistDto lugarDto, L lugar) {
        lugar.setCc(lugarDto.getCc());
        lugar.setCity(lugarDto.getCity());
        lugar.setPostalCode(lugarDto.getPostalCode());
        lugar.setAddress(lugarDto.getAddress());
        lugar.setLat(lugarDto.getLat());
        lugar.setLng(lugarDto.getLng());
        lugar.setName(lugarDto.getName());
        lugar.setCountry(lugarDto.getCountry());
        lugar.setProvince(lugarDto.getProvince());
        lugar.setVerified(lugarDto.getVerified());
        return lugar;
    }
}
