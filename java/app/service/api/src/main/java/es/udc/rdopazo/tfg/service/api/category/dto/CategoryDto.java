package es.udc.rdopazo.tfg.service.api.category.dto;

import java.util.List;

import es.udc.rdopazo.tfg.service.api.subcategory.dto.SubCategoryDto;
import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class CategoryDto implements EntityDto {

    private static final long serialVersionUID = -5757113378687925099L;

    private Long id;

    private String nombre;

    private String id_foursquare;

    private String icon;

    private List<SubCategoryDto> sub_categorias;

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
    public List<SubCategoryDto> getSub_categorias() {
        return this.sub_categorias;
    }

    /**
     * Sets the sub_categorias to given value
     *
     * @param sub_categorias
     *            The sub_categorias to set
     */
    public void setSub_categorias(List<SubCategoryDto> sub_categorias) {
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
