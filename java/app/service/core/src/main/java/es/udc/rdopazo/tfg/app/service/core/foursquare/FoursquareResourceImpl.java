package es.udc.rdopazo.tfg.app.service.core.foursquare;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.foursquare.FoursquareService;
import es.udc.rdopazo.tfg.app.model.core.google.Pruebas;
import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.service.core.foursquare.converter.FoursquareEntityToDtoConverter;
import es.udc.rdopazo.tfg.service.api.foursquare.FoursquareResource;
import es.udc.rdopazo.tfg.service.api.place.dto.PlaceDto;

@Service
public class FoursquareResourceImpl<R extends Route<D, ?>, D extends RouteDay<?>> implements FoursquareResource {

    @Autowired
    FoursquareService fsService;

    @Autowired
    RouteService<R, D> routeService;

    @Autowired
    Pruebas pruebas;

    @Autowired
    FoursquareEntityToDtoConverter converter;

    public List<PlaceDto> getPlacesByCity(String route, String nombre, String limit, String category, String photos) {

        boolean photosBol = Boolean.parseBoolean(photos);
        Long idRouteLong = null;
        try {
            idRouteLong = Long.parseLong(route);
        } catch (NumberFormatException e) {

        }

        List<PlaceDto> placeList = this.converter.compactVenueToLugarDtoList(
                this.fsService.searchPlaces(nombre, null, null, null, Integer.parseInt(limit), category));

        for (PlaceDto place : placeList) {
            if (photosBol) {
                // place.setPhoto(this.fsService.getPhoto(place.getIdFoursquare()));
            }
            // place.setLikes(this.fsService.getNumLikes(place.getIdFoursquare()));
            if (idRouteLong != null) {
                this.setNumDaysAsigned(idRouteLong, place);
            }
        }

        return placeList;
    }

    private void setNumDaysAsigned(Long route, PlaceDto lugarDto) {
        lugarDto.setAssignedDays((this.routeService.getRouteDaysByRotueAndPlace(route, lugarDto.getIdFoursquare())));
    }

    public String getCoord(String lat, String lng, String time) {

        System.out.println("lat: " + lat);
        System.out.println("lng: " + lng);
        System.out.println("time: " + time);
        return "hola";
    }

    public List<PlaceDto> getPlacesByCoord(String route, String nombre, String limit, String category, String photos,
            List<String> categorias) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<PlaceDto> searchPlaces(String route, String lat, String lng, String near, String intent, String radius,
            String query, String limit, String category, String photo) {
        boolean photosBol = Boolean.parseBoolean(photo);
        Long idRouteLong = null;
        Integer radiusInt = null;

        try {
            radiusInt = Integer.parseInt(radius);
        } catch (NumberFormatException e) {

        }

        try {
            idRouteLong = Long.parseLong(route);
        } catch (NumberFormatException e) {

        }

        List<PlaceDto> placeList = this.converter.compactVenueToLugarDtoList(
                this.fsService.searchPlaces(lat, lng, intent, radiusInt, query, Integer.parseInt(limit), category));
        for (PlaceDto place : placeList) {
            if (idRouteLong != null) {
                this.setNumDaysAsigned(idRouteLong, place);
            }
        }
        return placeList;
    }

    public List<PlaceDto> recommendedPlaces(String route, String lat, String lng, String near, String radius,
            String section, String query, String limit, String sortByDistance, String price, String photo) {

        boolean photosBol = Boolean.parseBoolean(photo);
        Long idRouteLong = null;
        Integer radiusInt = null;
        Integer sortInt = null;

        try {
            radiusInt = Integer.parseInt(radius);
        } catch (NumberFormatException e) {

        }

        try {
            sortInt = Integer.parseInt(sortByDistance);
        } catch (NumberFormatException e) {

        }

        try {
            idRouteLong = Long.parseLong(route);
        } catch (NumberFormatException e) {

        }

        List<PlaceDto> placeList = this.converter.recommendVenueToLugarDtoList(this.fsService.recommendedPlaces(lat,
                lng, radiusInt, section, query, Integer.parseInt(limit), sortInt, price));
        for (PlaceDto place : placeList) {
            if (idRouteLong != null) {
                this.setNumDaysAsigned(idRouteLong, place);
            }
        }
        return placeList;

    }

    public String getFoursquareCategories() {
        this.fsService.getFoursquareCategories();
        return null;
    }

}
