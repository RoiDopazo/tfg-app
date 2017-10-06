package es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.rutalugar.JpaRutaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.usuario.JpaUsuario;

@Entity
@Table(name = "ROUTE")
@SequenceGenerator(name = "route_seq", sequenceName = "ROUTE_SEQ", allocationSize = 1)
public class JpaRuta implements Ruta<JpaRutaLugar> {

    private static final long serialVersionUID = -7273214408089284378L;

    @Id
    @Column(name = "X_ROUTE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_seq")
    private Long id;

    @Column(name = "STATE")
    private String state;

    @Column(name = "NUM_PLACES")
    private Long num_places;

    @Column(name = "CITY")
    private String city;

    @Column(name = "DISTANCE")
    private Long distance;

    @Column(name = "TIME")
    private Long time;

    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL)
    private List<JpaRutaLugar> ruta_lugares;

    @ManyToOne
    @JoinColumn(name = "USER_X_USER")
    private JpaUsuario user;

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
     * Returns the state
     * 
     * @return The state
     */
    public String getState() {
        return this.state;
    }

    /**
     * Sets the state to given value
     * 
     * @param state
     *            The state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns the num_places
     * 
     * @return The num_places
     */
    public Long getNum_places() {
        return this.num_places;
    }

    /**
     * Sets the num_places to given value
     * 
     * @param num_places
     *            The num_places to set
     */
    public void setNum_places(Long num_places) {
        this.num_places = num_places;
    }

    /**
     * Returns the city
     * 
     * @return The city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Sets the city to given value
     * 
     * @param city
     *            The city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the distance
     * 
     * @return The distance
     */
    public Long getDistance() {
        return this.distance;
    }

    /**
     * Sets the distance to given value
     * 
     * @param distance
     *            The distance to set
     */
    public void setDistance(Long distance) {
        this.distance = distance;
    }

    /**
     * Returns the time
     * 
     * @return The time
     */
    public Long getTime() {
        return this.time;
    }

    /**
     * Sets the time to given value
     * 
     * @param time
     *            The time to set
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * Returns the ruta_lugares
     * 
     * @return The ruta_lugares
     */
    public List<JpaRutaLugar> getRuta_lugares() {
        return this.ruta_lugares;
    }

    /**
     * Sets the ruta_lugares to given value
     * 
     * @param ruta_lugares
     *            The ruta_lugares to set
     */
    public void setRuta_lugares(List<JpaRutaLugar> ruta_lugares) {
        this.ruta_lugares = ruta_lugares;
    }

    /**
     * Returns the user
     * 
     * @return The user
     */
    public JpaUsuario getUser() {
        return this.user;
    }

    /**
     * Sets the user to given value
     * 
     * @param user
     *            The user to set
     */
    public void setUser(JpaUsuario user) {
        this.user = user;
    }

}
