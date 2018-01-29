package es.udc.rdopazo.tfg.app.service.core.foursquare.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.service.api.place.dto.PlaceDto;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.Recommendation;

@Component
public class FoursquareEntityToDtoConverter {

    public PlaceDto compactVenueToLugarDto(CompactVenue compactVenue) {
        PlaceDto lugar = new PlaceDto();
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

        if (compactVenue.getCategories().length != 0) {
            if (compactVenue.getCategories()[0].getIcon() != null) {
                lugar.setCategoryIcon(compactVenue.getCategories()[0].getIcon().getPrefix() + "64"
                        + compactVenue.getCategories()[0].getIcon().getSuffix());
            }
            lugar.setCategoryName(compactVenue.getCategories()[0].getName());
        }

        return lugar;
    }

    public List<PlaceDto> compactVenueToLugarDtoList(CompactVenue[] compactVenues) {
        List<PlaceDto> lugarDtos = new ArrayList<PlaceDto>();
        for (CompactVenue compactVenue : compactVenues) {
            lugarDtos.add(this.compactVenueToLugarDto(compactVenue));
        }
        return lugarDtos;
    }

    public List<PlaceDto> recommendVenueToLugarDtoList(Recommendation[] recomendationVenues) {
        List<PlaceDto> lugarDtos = new ArrayList<PlaceDto>();
        for (Recommendation recommendationVenue : recomendationVenues) {
            lugarDtos.add(this.compactVenueToLugarDto(recommendationVenue.getVenue()));
        }
        return lugarDtos;
    }

}
