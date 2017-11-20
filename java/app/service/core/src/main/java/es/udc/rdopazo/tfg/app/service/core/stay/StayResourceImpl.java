package es.udc.rdopazo.tfg.app.service.core.stay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.stay.StayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.place.JpaVisitaLugar;
import es.udc.rdopazo.tfg.service.api.stay.StayResource;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayDto;

@Service
public class StayResourceImpl<D extends RouteDay<?>, L extends Place, V extends Stay<?, ?>> implements StayResource {

    @Autowired
    private StayService<V> service;

    public List<StayDto> getAll(String idRoute, String idDay, String index, String count) {
        V vl = (V) new JpaVisitaLugar();

        vl.setDay(null);
        vl.setTime(12121L);
        vl.setTravelMode("ANDANDO");
        vl.setOrder(1);

        this.service.add(vl);
        return null;
    }

}
