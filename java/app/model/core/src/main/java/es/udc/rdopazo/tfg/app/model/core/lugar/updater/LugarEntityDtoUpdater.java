package es.udc.rdopazo.tfg.app.model.core.lugar.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.lugar.dto.LugarDto;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;

@Component
public class LugarEntityDtoUpdater<L extends Lugar> {

    public L update(LugarDto lugarDto, L lugar) {
        lugar.setCc(lugarDto.getCc());
        lugar.setCiudad(lugarDto.getCiudad());
        lugar.setCodigo_postal(lugarDto.getCodigo_postal());
        lugar.setDireccion(lugarDto.getDireccion());
        lugar.setEmail(lugarDto.getEmail());
        lugar.setFacebook(lugarDto.getFacebook());
        lugar.setId_foursquare(lugarDto.getId_foursquare());
        lugar.setLat(lugarDto.getLat());
        lugar.setLng(lugarDto.getLng());
        lugar.setNombre(lugarDto.getNombre());
        lugar.setPais(lugarDto.getPais());
        lugar.setProvincia(lugarDto.getProvincia());
        lugar.setTelefono(lugarDto.getTelefono());
        lugar.setTwitter(lugarDto.getTwitter());
        lugar.setVerificado(lugarDto.getVerificado());
        return lugar;
    }
}
