package es.udc.rdopazo.tfg.service.api.category.dto;

import java.util.List;

import es.udc.rdopazo.tfg.service.api.subcategory.dto.SubCategoryDto;
import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class CategoryDto implements EntityDto {

    private static final long serialVersionUID = -5757113378687925099L;

    private Long id;

    private String name;

    private String pluralName;

    private String iconPrefix;

    private String iconSuffix;

    private String id_foursquare;

    private List<SubCategoryDto> subCategories;

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
     * Returns the name
     * 
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name to given value
     * 
     * @param name
     *            The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the pluralName
     * 
     * @return The pluralName
     */
    public String getPluralName() {
        return this.pluralName;
    }

    /**
     * Sets the pluralName to given value
     * 
     * @param pluralName
     *            The pluralName to set
     */
    public void setPluralName(String pluralName) {
        this.pluralName = pluralName;
    }

    /**
     * Returns the iconPrefix
     * 
     * @return The iconPrefix
     */
    public String getIconPrefix() {
        return this.iconPrefix;
    }

    /**
     * Sets the iconPrefix to given value
     * 
     * @param iconPrefix
     *            The iconPrefix to set
     */
    public void setIconPrefix(String iconPrefix) {
        this.iconPrefix = iconPrefix;
    }

    /**
     * Returns the iconSuffix
     * 
     * @return The iconSuffix
     */
    public String getIconSuffix() {
        return this.iconSuffix;
    }

    /**
     * Sets the iconSuffix to given value
     * 
     * @param iconSuffix
     *            The iconSuffix to set
     */
    public void setIconSuffix(String iconSuffix) {
        this.iconSuffix = iconSuffix;
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
     * Returns the subCategories
     * 
     * @return The subCategories
     */
    public List<SubCategoryDto> getSubCategories() {
        return this.subCategories;
    }

    /**
     * Sets the subCategories to given value
     * 
     * @param subCategories
     *            The subCategories to set
     */
    public void setSubCategories(List<SubCategoryDto> subCategories) {
        this.subCategories = subCategories;
    }

}
