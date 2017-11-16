package es.udc.rdopazo.tfg.app.service.core.visita;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.visita.VisitaService;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.visita.Visita;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.visita.lugar.JpaVisitaLugar;
import es.udc.rdopazo.tfg.service.api.visita.VisitaResource;
import es.udc.rdopazo.tfg.service.api.visita.dto.VisitaDto;

@Service
public class VisitaResourceImpl<D extends Dia<?>, L extends Lugar, V extends Visita<?, ?>> implements VisitaResource {

    @Autowired
    private VisitaService<V> service;

    public List<VisitaDto> getAll(String idRoute, String idDay, String index, String count) {
        V vl = (V) new JpaVisitaLugar();

        vl.setDay(null);
        vl.setTime(12121L);
        vl.setTravelMode("ANDANDO");
        vl.setOrder(1);

        this.service.add(vl);
        return null;
    }

}
