package es.udc.rdopazo.tfg.app.model.persistence.api.categoria;

import es.udc.rdopazo.tfg.app.model.persistence.util.Entidad;

public interface Categoria extends Entidad<Long> {

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
     * Returns the idFoursquare
     *
     * @return The idFoursquare
     */
    String getIdFoursquare();

    /**
     * Sets the idFoursquare to given value
     *
     * @param idFoursquare
     *            The idFoursquare to set
     */
    void setIdFoursquare(String idFoursquare);

}
