package es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.JpaRoute;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.id.RouteDayPK;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.JpaStay;

@Entity
@Table(name = "ROUTE_DAY")
public class JpaRouteDay implements RouteDay<JpaStay> {

    @EmbeddedId
    private RouteDayPK diaPK;

    @Column(name = "START_TIME")
    private Long startTime;

    @OneToMany(mappedBy = "day", fetch = FetchType.EAGER, orphanRemoval = true)
    @OrderBy("order ASC")
    private List<JpaStay> stays;

    @JoinColumn(name = "ROUTE_X_ROUTE", referencedColumnName = "X_ROUTE", insertable = false, updatable = false)
    @ManyToOne
    private JpaRoute route;

    /**
     * Returns the diaPK
     *
     * @return The diaPK
     */
    public RouteDayPK getDiaPK() {
        return this.diaPK;
    }

    /**
     * Sets the diaPK to given value
     *
     * @param diaPK
     *            The diaPK to set
     */
    public void setDiaPK(RouteDayPK diaPK) {
        this.diaPK = diaPK;
    }

    /**
     * Returns the route
     *
     * @return The route
     */
    public JpaRoute getRoute() {
        return this.route;
    }

    /**
     * Sets the route to given value
     *
     * @param route
     *            The route to set
     */
    public void setRoute(JpaRoute route) {
        this.route = route;
    }

    /**
     * Returns the dayPlaces
     *
     * @return The dayPlaces
     */
    public List<JpaStay> getStays() {
        return this.stays;
    }

    /**
     * Sets the dayPlaces to given value
     *
     * @param dayPlaces
     *            The dayPlaces to set
     */
    public void setStays(List<JpaStay> stays) {
        this.stays = stays;
    }

    /**
     * Returns the startTime
     *
     * @return The startTime
     */
    public Long getStartTime() {
        return this.startTime;
    }

    /**
     * Sets the startTime to given value
     *
     * @param startTime
     *            The startTime to set
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

}
