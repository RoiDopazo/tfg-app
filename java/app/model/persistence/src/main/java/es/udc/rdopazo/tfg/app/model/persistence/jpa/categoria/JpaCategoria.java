package es.udc.rdopazo.tfg.app.model.persistence.jpa.categoria;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.subcategoria.JpaSubCategoria;

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

    @Column(name = "ICON")
    private String icon;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "categoria")
    private List<JpaSubCategoria> sub_categorias;

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

    /**
     * Returns the sub_categorias
     *
     * @return The sub_categorias
     */
    public List<JpaSubCategoria> getSub_categorias() {
        return this.sub_categorias;
    }

    /**
     * Sets the sub_categorias to given value
     *
     * @param sub_categorias
     *            The sub_categorias to set
     */
    public void setSub_categorias(List<JpaSubCategoria> sub_categorias) {
        this.sub_categorias = sub_categorias;
    }

    /**
     * Returns the icon
     * 
     * @return The icon
     */
    public String getIcon() {
        return this.icon;
    }

    /**
     * Sets the icon to given value
     * 
     * @param icon
     *            The icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

}
