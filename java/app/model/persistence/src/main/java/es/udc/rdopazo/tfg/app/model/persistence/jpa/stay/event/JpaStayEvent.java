package es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import es.udc.rdopazo.tfg.app.model.persistence.api.stay.event.StayEvent;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.place.JpaEventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.place.JpaPlace;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.JpaRouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.JpaStay;

@Entity
@DiscriminatorValue(value = "EP")
public class JpaStayEvent extends JpaStay implements StayEvent<JpaRouteDay, JpaPlace, JpaEventPlace> {

    private static final long serialVersionUID = 4079295651375429616L;

}
