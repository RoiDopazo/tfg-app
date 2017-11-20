package es.udc.rdopazo.tfg.app.service.core.place.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.service.api.place.dto.PlaceDto;

@Component
public class PlaceEntityDtoUpdater<L extends Place> {

    public L update(PlaceDto lugarDto, L lugar) {
        lugar.setCc(lugarDto.getCc());
        lugar.setCity(lugarDto.getCity());
        lugar.setPostalCode(lugarDto.getPostalCode());
        lugar.setAddress(lugarDto.getAddress());
        lugar.setEmail(lugarDto.getEmail());
        lugar.setFacebook(lugarDto.getFacebook());
        lugar.setIdFoursquare(lugarDto.getIdFoursquare());
        lugar.setLat(lugarDto.getLat());
        lugar.setLng(lugarDto.getLng());
        lugar.setName(lugarDto.getName());
        lugar.setCountry(lugarDto.getCountry());
        lugar.setProvince(lugarDto.getProvince());
        lugar.setPhone(lugarDto.getPhone());
        lugar.setTwitter(lugarDto.getTwitter());
        lugar.setVerified(lugarDto.getVerified());
        return lugar;
    }
}
