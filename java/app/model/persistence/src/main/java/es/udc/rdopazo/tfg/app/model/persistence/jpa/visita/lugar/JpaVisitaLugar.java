package es.udc.rdopazo.tfg.app.model.persistence.jpa.visita.lugar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import es.udc.rdopazo.tfg.app.model.persistence.api.visita.lugar.VisitaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.dia.JpaDia;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.lugar.JpaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.visita.JpaVisita;

@Entity
@DiscriminatorValue(value = "PL")
public class JpaVisitaLugar extends JpaVisita implements VisitaLugar<JpaDia, JpaLugar> {

    private static final long serialVersionUID = -4274931205330889092L;

}
