package es.udc.rdopazo.tfg.app.service.core.lugar.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.service.api.lugar.dto.LugarDto;

@Component
public class LugarEntityDtoUpdater<L extends Lugar> {

    public L update(LugarDto lugarDto, L lugar) {
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
