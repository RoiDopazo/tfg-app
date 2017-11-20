package es.udc.rdopazo.tfg.app.model.persistence.api.route.day;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.DiaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.JpaRoute;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.id.RouteDayPK;

public interface RouteDay<DL extends DiaLugar<?, ?>> {

    /**
     * Returns the diaPK
     *
     * @return The diaPK
     */
    RouteDayPK getDiaPK();

    /**
     * Sets the diaPK to given value
     *
     * @param diaPK
     *            The diaPK to set
     */
    void setDiaPK(RouteDayPK diaPK);

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
    JpaRoute getRoute();

    /**
     * Sets the route to given value
     *
     * @param route
     *            The route to set
     */
    void setRoute(JpaRoute route);

    /**
     * Returns the dayPlaces
     *
     * @return The dayPlaces
     */
    List<DL> getDayPlaces();

    /**
     * Sets the dayPlaces to given value
     *
     * @param dayPlaces
     *            The dayPlaces to set
     */
    void setDayPlaces(List<DL> dayPlaces);

    /**
     * Returns the startTime
     *
     * @return The startTime
     */
    Long getStartTime();

    /**
     * Sets the startTime to given value
     *
     * @param startTime
     *            The startTime to set
     */
    void setStartTime(Long startTime);

}
