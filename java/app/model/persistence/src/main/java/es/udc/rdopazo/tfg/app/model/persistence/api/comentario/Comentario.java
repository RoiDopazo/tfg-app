package es.udc.rdopazo.tfg.app.model.persistence.api.comentario;

import es.udc.rdopazo.tfg.app.model.persistence.util.Entidad;

public interface Comentario extends Entidad<Long> {

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
     * Returns the titulo
     * 
     * @return The titulo
     */
    String getTitulo();

    /**
     * Sets the titulo to given value
     * 
     * @param titulo
     *            The titulo to set
     */
    void setTitulo(String titulo);

    /**
     * Returns the cuerpo
     * 
     * @return The cuerpo
     */
    String getCuerpo();

    /**
     * Sets the cuerpo to given value
     * 
     * @param cuerpo
     *            The cuerpo to set
     */
    void setCuerpo(String cuerpo);

    /**
     * Returns the estado
     * 
     * @return The estado
     */
    String getEstado();

    /**
     * Sets the estado to given value
     * 
     * @param estado
     *            The estado to set
     */
    void setEstado(String estado);

    /**
     * Returns the razon
     * 
     * @return The razon
     */
    String getRazon();

    /**
     * Sets the razon to given value
     * 
     * @param razon
     *            The razon to set
     */
    void setRazon(String razon);
}
