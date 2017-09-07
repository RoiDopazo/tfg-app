package es.udc.rdopazo.tfg.service.api.comentario.dto;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class ComentarioDto implements EntityDto {

    private static final long serialVersionUID = 880917946516037320L;

    private Long id;

    private String titulo;

    private String cuerpo;

    private String estado;

    private String razon;

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
     * Returns the titulo
     *
     * @return The titulo
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Sets the titulo to given value
     *
     * @param titulo
     *            The titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Returns the cuerpo
     *
     * @return The cuerpo
     */
    public String getCuerpo() {
        return this.cuerpo;
    }

    /**
     * Sets the cuerpo to given value
     *
     * @param cuerpo
     *            The cuerpo to set
     */
    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    /**
     * Returns the estado
     *
     * @return The estado
     */
    public String getEstado() {
        return this.estado;
    }

    /**
     * Sets the estado to given value
     *
     * @param estado
     *            The estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Returns the razon
     *
     * @return The razon
     */
    public String getRazon() {
        return this.razon;
    }

    /**
     * Sets the razon to given value
     *
     * @param razon
     *            The razon to set
     */
    public void setRazon(String razon) {
        this.razon = razon;
    }

}
