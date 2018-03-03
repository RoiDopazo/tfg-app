package es.udc.rdopazo.tfg.app.model.persistence.jpa.realtimedata;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.RealTimeData;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.JpaRouteDay;

@Entity
@Table(name = "REAL_TIME_DATA")
@SequenceGenerator(name = "real_time_data_seq", sequenceName = "REAL_TIME_DATA_SEQ", allocationSize = 1)
public class JpaRealTimeData implements RealTimeData<JpaRouteDay> {

    private static final long serialVersionUID = -8605917519905203011L;

    @Id
    @Column(name = "X_RDATA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "real_time_data_seq")
    private Long id;

    @Column(name = "LOCATIONS")
    @Lob
    private String locations;

    @OneToOne(mappedBy = "realTimeData", cascade = CascadeType.ALL, orphanRemoval = true)
    private JpaRouteDay routeDay;

    /**
     * Returns the id
     *
     * @return The id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id to given value
     *
     * @param id
     *            The id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the locations
     *
     * @return The locations
     */
    public String getLocations() {
        return this.locations;
    }

    /**
     * Sets the locations to given value
     *
     * @param locations
     *            The locations to set
     */
    public void setLocations(String locations) {
        this.locations = locations;
    }

    public void appendLocations(String locations) {
        this.locations += locations.trim();
    }

    /**
     * Returns the routeDay
     *
     * @return The routeDay
     */
    public JpaRouteDay getRouteDay() {
        return this.routeDay;
    }

    /**
     * Sets the routeDay to given value
     *
     * @param routeDay
     *            The routeDay to set
     */
    public void setRouteDay(JpaRouteDay routeDay) {
        this.routeDay = routeDay;
    }

}
