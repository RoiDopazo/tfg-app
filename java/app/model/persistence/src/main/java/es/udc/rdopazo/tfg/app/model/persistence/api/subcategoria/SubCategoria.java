package es.udc.rdopazo.tfg.app.model.persistence.api.subcategoria;

import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;
import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public interface SubCategoria<C extends Categoria> extends Entity<Long> {

    /**
     * Returns the id
     *
     * @return The id
     */
    Long getId();

    /**
     * Sets the id to given value
     *
     * @param id
     *            The id to set
     */
    void setId(Long id);

    /**
     * Returns the nombre
     *
     * @return The nombre
     */
    String getNombre();

    /**
     * Sets the nombre to given value
     *
     * @param nombre
     *            The nombre to set
     */
    void setNombre(String nombre);

    /**
     * Returns the id_foursquare
     *
     * @return The id_foursquare
     */
    String getId_foursquare();

    /**
     * Sets the id_foursquare to given value
     *
     * @param id_foursquare
     *            The id_foursquare to set
     */
    void setId_foursquare(String id_foursquare);

    /**
     * Returns the categoria
     *
     * @return The categoria
     */
    C getCategoria();

    /**
     * Sets the categoria to given value
     *
     * @param categoria
     *            The categoria to set
     */
    void setCategoria(C categoria);
}
