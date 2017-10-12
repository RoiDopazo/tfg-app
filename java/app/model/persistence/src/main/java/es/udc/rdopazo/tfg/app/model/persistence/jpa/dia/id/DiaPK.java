package es.udc.rdopazo.tfg.app.model.persistence.jpa.dia.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DiaPK implements Serializable {

    @Column(name = "ROUTE_X_ROUTE")
    private Long idRoute;

    @Column(name = "X_RDAY")
    private Long idDay;

    public DiaPK() {
    }

    public DiaPK(Long id, Long size) {
        this.idRoute = id;
        this.idDay = size;
    }

    /**
     * Returns the idRoute
     * 
     * @return The idRoute
     */
    public Long getIdRoute() {
        return this.idRoute;
    }

    /**
     * Sets the idRoute to given value
     * 
     * @param idRoute
     *            The idRoute to set
     */
    public void setIdRoute(Long idRoute) {
        this.idRoute = idRoute;
    }

    /**
     * Returns the idDay
     * 
     * @return The idDay
     */
    public Long getIdDay() {
        return this.idDay;
    }

    /**
     * Sets the idDay to given value
     * 
     * @param idDay
     *            The idDay to set
     */
    public void setIdDay(Long idDay) {
        this.idDay = idDay;
    }

}
