package es.udc.rdopazo.tfg.app.api.subcategoria.dto;

import es.udc.rdopazo.tfg.app.api.categoria.dto.CategoriaDto;
import es.udc.rdopazo.tfg.app.api.util.EntityDto;

public class SubCategoriaDto implements EntityDto {

    private static final long serialVersionUID = -1756365160862180478L;

    private Long id;

    private String nombre;

    private String id_foursquare;

    private Long id_categoria;

    private CategoriaDto categoriaDto;

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
    public Long getId_categoria() {
        return this.id_categoria;
    }

    /**
     * Sets the id_categoria to given value
     *
     * @param id_categoria
     *            The id_categoria to set
     */
    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }

    /**
     * Returns the categoriaDto
     *
     * @return The categoriaDto
     */
    public CategoriaDto getCategoriaDto() {
        return this.categoriaDto;
    }

    /**
     * Sets the categoriaDto to given value
     *
     * @param categoriaDto
     *            The categoriaDto to set
     */
    public void setCategoriaDto(CategoriaDto categoriaDto) {
        this.categoriaDto = categoriaDto;
    }

}
