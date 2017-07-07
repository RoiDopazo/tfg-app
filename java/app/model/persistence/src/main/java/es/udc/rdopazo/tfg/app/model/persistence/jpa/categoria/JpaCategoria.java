package es.udc.rdopazo.tfg.app.model.persistence.jpa.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;

@Entity
@Table(name = "CATEGORY")
@SequenceGenerator(name = "categoria_seq", sequenceName = "CATEGORY_SEQ", allocationSize = 1)
public class JpaCategoria implements Categoria {

    private static final long serialVersionUID = -4748333198476880996L;

    @Id
    @Column(name = "X_CAT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_seq")
    private Long id;

    @Column(name = "NAME")
    private String nombre;

    @Column(name = "ID_FOURSQUARE")
    private String id_foursquare;

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
     * Returns the nombre
     *
     * @return The nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Sets the nombre to given value
     *
     * @param nombre
     *            The nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Returns the idFoursquare
     *
     * @return The idFoursquare
     */
    public String getId_foursquare() {
        return this.id_foursquare;
    }

    /**
     * Sets the idFoursquare to given value
     *
     * @param idFoursquare
     *            The idFoursquare to set
     */
    public void setId_foursquare(String id_foursquare) {
        this.id_foursquare = id_foursquare;
    }

}
