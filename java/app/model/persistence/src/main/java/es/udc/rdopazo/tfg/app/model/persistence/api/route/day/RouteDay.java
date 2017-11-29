package es.udc.rdopazo.tfg.app.model.persistence.api.route.day;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.JpaRoute;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.id.RouteDayPK;

public interface RouteDay<S extends Stay<?, ?, ?>> {

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
    List<S> getStays();

    /**
     * Sets the dayPlaces to given value
     *
     * @param dayPlaces
     *            The dayPlaces to set
     */
    void setStays(List<S> stays);

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
