package es.udc.rdopazo.tfg.app.service.core.foursquare;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.foursquare.FoursquareService;
import es.udc.rdopazo.tfg.app.service.core.foursquare.converter.FoursquareEntityToDtoConverter;
import es.udc.rdopazo.tfg.service.api.foursquare.FoursquareResource;
import es.udc.rdopazo.tfg.service.api.lugar.dto.LugarDto;

@Service
public class FoursquareResourceImpl implements FoursquareResource {

    @Autowired
    FoursquareService fsService;

    @Autowired
    FoursquareEntityToDtoConverter converter;

    public List<LugarDto> obtenerLugares(String nombre) {

        return this.converter.compactVenueToLugarDtoList(this.fsService.obtenerLugaresCiudad(nombre));

    }

}
