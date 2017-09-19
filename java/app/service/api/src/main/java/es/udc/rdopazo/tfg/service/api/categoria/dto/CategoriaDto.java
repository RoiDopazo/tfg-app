package es.udc.rdopazo.tfg.service.api.categoria.dto;

import java.util.List;

import es.udc.rdopazo.tfg.service.api.subcategoria.dto.SubCategoriaDto;
import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class CategoriaDto implements EntityDto {

    private static final long serialVersionUID = -5757113378687925099L;

    private Long id;

    private String nombre;

    private String id_foursquare;

    private List<SubCategoriaDto> sub_categorias;

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
     * Returns the sub_categorias
     *
     * @return The sub_categorias
     */
    public List<SubCategoriaDto> getSub_categorias() {
        return this.sub_categorias;
    }

    /**
     * Sets the sub_categorias to given value
     *
     * @param sub_categorias
     *            The sub_categorias to set
     */
    public void setSub_categorias(List<SubCategoriaDto> sub_categorias) {
        this.sub_categorias = sub_categorias;
    }

}
