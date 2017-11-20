package es.udc.rdopazo.tfg.app.model.persistence.api.category;

import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public interface Category extends Entity<Long> {

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
    String getId_foursquare();

    /**
     * Sets the idFoursquare to given value
     *
     * @param idFoursquare
     *            The idFoursquare to set
     */
    void setId_foursquare(String id_foursquare);

    /**
     * Returns the icon
     * 
     * @return The icon
     */
    public String getIcon();

    /**
     * Sets the icon to given value
     * 
     * @param icon
     *            The icon to set
     */
    public void setIcon(String icon);
}
