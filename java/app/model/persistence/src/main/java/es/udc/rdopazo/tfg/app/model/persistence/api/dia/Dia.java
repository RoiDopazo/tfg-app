package es.udc.rdopazo.tfg.app.model.persistence.api.dia;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.DiaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.dia.id.DiaPK;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta.JpaRuta;

public interface Dia<DL extends DiaLugar<?, ?>> {

    /**
     * Returns the diaPK
     *
     * @return The diaPK
     */
    public DiaPK getDiaPK();

    /**
     * Sets the diaPK to given value
     *
     * @param diaPK
     *            The diaPK to set
     */
    public void setDiaPK(DiaPK diaPK);

    /**
     * Returns the name
     *
     * @return The name
     */
    String getName();

    /**
     * Sets the name to given value
     *
     * @param name
     *            The name to set
     */
    void setName(String name);

    /**
     * Returns the order
     *
     * @return The order
     */
    Long getOrder();

    /**
     * Sets the order to given value
     *
     * @param order
     *            The order to set
     */
    void setOrder(Long order);

    /**
     * Returns the route
     *
     * @return The route
     */
    JpaRuta getRoute();

    /**
     * Sets the route to given value
     *
     * @param route
     *            The route to set
     */
    void setRoute(JpaRuta route);

    /**
     * Returns the dayPlaces
     *
     * @return The dayPlaces
     */
    public List<DL> getDayPlaces();

    /**
     * Sets the dayPlaces to given value
     *
     * @param dayPlaces
     *            The dayPlaces to set
     */
    public void setDayPlaces(List<DL> dayPlaces);
}
