package es.udc.rdopazo.tfg.app.service.core.place;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.service.api.place.PlaceResource;

@Service
public class PlaceResourceImpl<S extends Stay<?, ?, ?>, L extends Place> implements PlaceResource {

}
