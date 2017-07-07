package es.udc.rdopazo.tfg.app.model.persistence.api.subcategoria;

import es.udc.rdopazo.tfg.app.model.persistence.util.Entidad;

public interface SubCategoria extends Entidad<Long> {

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

}
