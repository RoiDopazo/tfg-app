package es.udc.rdopazo.tfg.app.model.persistence.jpa.subcategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.category.JpaCategory;

@Entity
@Table(name = "CATEGORY_SUB")
@SequenceGenerator(name = "category_sub_seq", sequenceName = "CATEGORY_SUB_SEQ", allocationSize = 1)
public class JpaSubCategory implements SubCategory<JpaCategory> {

    private static final long serialVersionUID = -2896345425986479754L;

    @Id
    @Column(name = "X_SUBC")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sub_seq")
    private Long id;

    @Column(name = "NAME")
    private String nombre;

    @Column(name = "ID_FOURSQUARE")
    private String id_foursquare;

    @ManyToOne
    @JoinColumn(name = "CAT_X_CAT")
    private JpaCategory categoria;

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
     * Returns the id_foursquare
     *
     * @return The id_foursquare
     */
    public String getId_foursquare() {
        return this.id_foursquare;
    }

    /**
     * Sets the id_foursquare to given value
     *
     * @param id_foursquare
     *            The id_foursquare to set
     */
    public void setId_foursquare(String id_foursquare) {
        this.id_foursquare = id_foursquare;
    }

    /**
     * Returns the categoria
     * 
     * @return The categoria
     */
    public JpaCategory getCategoria() {
        return this.categoria;
    }

    /**
     * Sets the categoria to given value
     * 
     * @param categoria
     *            The categoria to set
     */
    public void setCategoria(JpaCategory categoria) {
        this.categoria = categoria;
    }

}
