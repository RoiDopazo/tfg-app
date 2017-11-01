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
        lugar.setCity(compactVenue.getLocation().getCity());
        lugar.setPostalCode(compactVenue.getLocation().getPostalCode());
        lugar.setAddress(compactVenue.getLocation().getAddress());
        lugar.setEmail(compactVenue.getContact().getEmail());
        lugar.setFacebook(compactVenue.getContact().getFacebook());
        lugar.setIdFoursquare(compactVenue.getId());
        lugar.setLat(compactVenue.getLocation().getLat());
        lugar.setLng(compactVenue.getLocation().getLng());
        lugar.setName(compactVenue.getName());
        lugar.setCountry(compactVenue.getLocation().getCountry());
        lugar.setProvince(compactVenue.getLocation().getState());
        lugar.setPhone(compactVenue.getContact().getFormattedPhone());
        lugar.setTwitter(compactVenue.getContact().getTwitter());
        lugar.setVerified(compactVenue.getVerified());
        lugar.setCategoryIcon(compactVenue.getCategories()[0].getIcon().getPrefix() + "64"
                + compactVenue.getCategories()[0].getIcon().getSuffix());
        lugar.setCategoryName(compactVenue.getCategories()[0].getName());
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
