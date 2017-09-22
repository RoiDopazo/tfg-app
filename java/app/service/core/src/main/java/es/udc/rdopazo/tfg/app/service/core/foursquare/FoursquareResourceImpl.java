package es.udc.rdopazo.tfg.app.service.core.foursquare;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.categoria.CategoriaService;
import es.udc.rdopazo.tfg.app.model.core.foursquare.FoursquareService;
import es.udc.rdopazo.tfg.app.model.core.google.Pruebas;
import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;
import es.udc.rdopazo.tfg.app.service.core.foursquare.converter.FoursquareEntityToDtoConverter;
import es.udc.rdopazo.tfg.service.api.foursquare.FoursquareResource;
import es.udc.rdopazo.tfg.service.api.lugar.dto.LugarDto;

@Service
public class FoursquareResourceImpl implements FoursquareResource {

    @Autowired
    FoursquareService fsService;

    @Autowired
    Pruebas pruebas;

    @Autowired
    CategoriaService<Categoria> categoriaService;

    @Autowired
    FoursquareEntityToDtoConverter converter;

    public List<LugarDto> getPlacesByCity(String nombre, String limit, String idCategoria, String photos) {

        boolean photosBol = Boolean.parseBoolean(photos);

        List<LugarDto> listaLugares = this.converter.compactVenueToLugarDtoList(
                this.fsService.getPlacesByCity(nombre, Integer.parseInt(limit), idCategoria));

        for (LugarDto lugar : listaLugares) {
            if (photosBol) {
                lugar.setFoto(this.fsService.getPhoto(lugar.getId_foursquare()));
            }
            lugar.setNumeroLikes(this.fsService.getNumLikes(lugar.getId_foursquare()));
        }

        return listaLugares;
    }

    public String getCoord(String lat, String lng, String time) {

        System.out.println("lat: " + lat);
        System.out.println("lng: " + lng);
        System.out.println("time: " + time);
        return "hola";
    }

}
