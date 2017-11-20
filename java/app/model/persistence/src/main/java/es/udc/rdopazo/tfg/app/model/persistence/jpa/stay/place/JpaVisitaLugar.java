package es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.place;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import es.udc.rdopazo.tfg.app.model.persistence.api.stay.place.VisitaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.place.JpaPlace;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.JpaRouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.JpaStay;

@Entity
@DiscriminatorValue(value = "PL")
public class JpaVisitaLugar extends JpaStay implements VisitaLugar<JpaRouteDay, JpaPlace> {

    private static final long serialVersionUID = -4274931205330889092L;

}
