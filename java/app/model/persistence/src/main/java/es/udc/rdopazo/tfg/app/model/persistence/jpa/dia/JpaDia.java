package es.udc.rdopazo.tfg.app.model.persistence.jpa.dia;

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

import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.dia.id.DiaPK;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.dialugar.JpaDiaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta.JpaRuta;

@Entity
@Table(name = "DAY")
public class JpaDia implements Dia<JpaDiaLugar> {

    @EmbeddedId
    private DiaPK diaPK;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ORDER_")
    private Long order;

    @Column(name = "START_TIME")
    private Long startTime;

    @OneToMany(mappedBy = "day", fetch = FetchType.EAGER, orphanRemoval = true)
    @OrderBy("order ASC")
    private List<JpaDiaLugar> dayPlaces;

    @JoinColumn(name = "ROUTE_X_ROUTE", referencedColumnName = "X_ROUTE", insertable = false, updatable = false)
    @ManyToOne
    private JpaRuta route;

    /**
     * Returns the diaPK
     *
     * @return The diaPK
     */
    public DiaPK getDiaPK() {
        return this.diaPK;
    }

    /**
     * Sets the diaPK to given value
     *
     * @param diaPK
     *            The diaPK to set
     */
    public void setDiaPK(DiaPK diaPK) {
        this.diaPK = diaPK;
    }

    /**
     * Returns the name
     *
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name to given value
     *
     * @param name
     *            The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the order
     *
     * @return The order
     */
    public Long getOrder() {
        return this.order;
    }

    /**
     * Sets the order to given value
     *
     * @param order
     *            The order to set
     */
    public void setOrder(Long order) {
        this.order = order;
    }

    /**
     * Returns the route
     *
     * @return The route
     */
    public JpaRuta getRoute() {
        return this.route;
    }

    /**
     * Sets the route to given value
     *
     * @param route
     *            The route to set
     */
    public void setRoute(JpaRuta route) {
        this.route = route;
    }

    /**
     * Returns the dayPlaces
     *
     * @return The dayPlaces
     */
    public List<JpaDiaLugar> getDayPlaces() {
        return this.dayPlaces;
    }

    /**
     * Sets the dayPlaces to given value
     *
     * @param dayPlaces
     *            The dayPlaces to set
     */
    public void setDayPlaces(List<JpaDiaLugar> dayPlaces) {
        this.dayPlaces = dayPlaces;
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
