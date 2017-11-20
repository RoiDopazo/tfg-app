package es.udc.rdopazo.tfg.app.service.core.foursquare;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.category.CategoryService;
import es.udc.rdopazo.tfg.app.model.core.foursquare.FoursquareService;
import es.udc.rdopazo.tfg.app.model.core.google.Pruebas;
import es.udc.rdopazo.tfg.app.model.core.route.day.RouteDayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.service.core.foursquare.converter.FoursquareEntityToDtoConverter;
import es.udc.rdopazo.tfg.service.api.foursquare.FoursquareResource;
import es.udc.rdopazo.tfg.service.api.place.dto.PlaceDto;

@Service
public class FoursquareResourceImpl<R extends Route<D>, D extends RouteDay<?>> implements FoursquareResource {

    @Autowired
    FoursquareService fsService;

    @Autowired
    RouteDayService<R, D> diaService;

    @Autowired
    Pruebas pruebas;

    @Autowired
    CategoryService<Category> categoriaService;

    @Autowired
    FoursquareEntityToDtoConverter converter;

    public List<PlaceDto> getPlacesByCity(String route, String nombre, String limit, String category, String photos) {

        boolean photosBol = Boolean.parseBoolean(photos);
        Long idRouteLong = null;
        try {
            idRouteLong = Long.parseLong(route);
        } catch (NumberFormatException e) {

        }

        List<PlaceDto> listaLugares = this.converter
                .compactVenueToLugarDtoList(this.fsService.getPlacesByCity(nombre, Integer.parseInt(limit), category));

        for (PlaceDto lugar : listaLugares) {
            if (photosBol) {
                // lugar.setPhoto(this.fsService.getPhoto(lugar.getIdFoursquare()));
            }
            // lugar.setLikes(this.fsService.getNumLikes(lugar.getIdFoursquare()));
            if (idRouteLong != null) {
                this.setNumDaysAsigned(idRouteLong, lugar);
            }
        }

        return listaLugares;
    }

    private void setNumDaysAsigned(Long route, PlaceDto lugarDto) {
        lugarDto.setAssignedDays((this.diaService.getListDaysByRotueAndPlace(route, lugarDto.getIdFoursquare())));
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

}
