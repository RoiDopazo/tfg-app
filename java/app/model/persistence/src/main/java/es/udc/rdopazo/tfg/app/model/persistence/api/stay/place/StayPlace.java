package es.udc.rdopazo.tfg.app.model.persistence.api.stay.place;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;

public interface StayPlace<D extends RouteDay<?, ?>, L extends Place, EP extends EventPlace<?>> extends Stay<D, L, EP> {

}
