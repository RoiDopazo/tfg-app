package es.udc.rdopazo.tfg.app.model.persistence.jpa.realtimedata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.RealTimeData;

@Entity
@Table(name = "REAL_TIME_DATA")
@SequenceGenerator(name = "real_time_data_seq", sequenceName = "REAL_TIME_DATA_SEQ", allocationSize = 1)
public class JpaRealTimeData implements RealTimeData {

    private static final long serialVersionUID = -8605917519905203011L;

    @Id
    @Column(name = "X_RDATA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "real_time_data_seq")
    private Long id;

    @Column(name = "LOCATIONS")
    @Lob
    private String locations;

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

}
