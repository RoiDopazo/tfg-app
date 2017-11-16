package es.udc.rdopazo.tfg.app.model.persistence.api.visita.lugar;

import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.visita.Visita;

public interface VisitaLugar<D extends Dia<?>, L extends Lugar> extends Visita<D, L> {

}
