package es.udc.rdopazo.tfg.app.service.core.foursquare.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.service.api.lugar.dto.LugarDto;
import fi.foyt.foursquare.api.entities.CompactVenue;

@Component
public class FoursquareEntityToDtoConverter {

    public LugarDto compactVenueToLugarDto(CompactVenue compactVenue) {
        LugarDto lugar = new LugarDto();
        lugar.setCc(compactVenue.getLocation().getCc());
        lugar.setCiudad(compactVenue.getLocation().getCity());
        lugar.setCodigo_postal(compactVenue.getLocation().getPostalCode());
        lugar.setDireccion(compactVenue.getLocation().getAddress());
        lugar.setEmail(compactVenue.getContact().getEmail());
        lugar.setFacebook(compactVenue.getContact().getFacebook());
        lugar.setId_foursquare(compactVenue.getId());
        lugar.setLat(compactVenue.getLocation().getLat());
        lugar.setLng(compactVenue.getLocation().getLng());
        lugar.setNombre(compactVenue.getName());
        lugar.setPais(compactVenue.getLocation().getCountry());
        lugar.setProvincia(compactVenue.getLocation().getState());
        lugar.setTelefono(compactVenue.getContact().getFormattedPhone());
        lugar.setTwitter(compactVenue.getContact().getTwitter());
        lugar.setVerificado(compactVenue.getVerified());
        lugar.setCategoriaIcon(compactVenue.getCategories()[0].getIcon().getPrefix() + "bg_64"
                + compactVenue.getCategories()[0].getIcon().getSuffix());
        lugar.setCategoriaName(compactVenue.getCategories()[0].getName());
        return lugar;
    }

    public List<LugarDto> compactVenueToLugarDtoList(CompactVenue[] compactVenues) {
        List<LugarDto> lugarDtos = new ArrayList<LugarDto>();
        for (CompactVenue compactVenue : compactVenues) {
            lugarDtos.add(this.compactVenueToLugarDto(compactVenue));
        }
        return lugarDtos;
    }

}
