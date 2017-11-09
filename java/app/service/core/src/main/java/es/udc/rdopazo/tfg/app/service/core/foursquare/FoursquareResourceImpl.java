package es.udc.rdopazo.tfg.app.service.core.foursquare;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.categoria.CategoriaService;
import es.udc.rdopazo.tfg.app.model.core.dia.DiaService;
import es.udc.rdopazo.tfg.app.model.core.foursquare.FoursquareService;
import es.udc.rdopazo.tfg.app.model.core.google.Pruebas;
import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.service.core.foursquare.converter.FoursquareEntityToDtoConverter;
import es.udc.rdopazo.tfg.service.api.foursquare.FoursquareResource;
import es.udc.rdopazo.tfg.service.api.lugar.dto.LugarDto;

@Service
public class FoursquareResourceImpl<R extends Ruta<D>, D extends Dia<?>> implements FoursquareResource {

    @Autowired
    FoursquareService fsService;

    @Autowired
    DiaService<R, D> diaService;

    @Autowired
    Pruebas pruebas;

    @Autowired
    CategoriaService<Categoria> categoriaService;

    @Autowired
    FoursquareEntityToDtoConverter converter;

    public List<LugarDto> getPlacesByCity(String route, String nombre, String limit, String category, String photos) {

        boolean photosBol = Boolean.parseBoolean(photos);
        Long idRouteLong = null;
        try {
            idRouteLong = Long.parseLong(route);
        } catch (NumberFormatException e) {

        }

        List<LugarDto> listaLugares = this.converter
                .compactVenueToLugarDtoList(this.fsService.getPlacesByCity(nombre, Integer.parseInt(limit), category));

        for (LugarDto lugar : listaLugares) {
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

    private void setNumDaysAsigned(Long route, LugarDto lugarDto) {
        lugarDto.setAssignedDays((this.diaService.getListDaysByRotueAndPlace(route, lugarDto.getIdFoursquare())));
    }

    public String getCoord(String lat, String lng, String time) {

        System.out.println("lat: " + lat);
        System.out.println("lng: " + lng);
        System.out.println("time: " + time);
        return "hola";
    }

    public List<LugarDto> getPlacesByCoord(String route, String nombre, String limit, String category, String photos,
            List<String> categorias) {
        // TODO Auto-generated method stub
        return null;
    }

}
