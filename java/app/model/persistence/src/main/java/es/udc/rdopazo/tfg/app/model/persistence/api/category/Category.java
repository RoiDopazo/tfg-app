package es.udc.rdopazo.tfg.app.model.persistence.api.category;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.jpa.subcategory.JpaSubCategory;
import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public interface Category extends Entity<Long> {

    /**
     * Returns the id
     *
     * @return The id
     */
    public Long getId();

    /**
     * Sets the id to given value
     *
     * @param id
     *            The id to set
     */
    public void setId(Long id);

    /**
     * Returns the name
     *
     * @return The name
     */
    public String getName();

    /**
     * Sets the name to given value
     *
     * @param name
     *            The name to set
     */
    public void setName(String name);

    /**
     * Returns the pluralName
     *
     * @return The pluralName
     */
    public String getPluralName();

    /**
     * Sets the pluralName to given value
     *
     * @param pluralName
     *            The pluralName to set
     */
    public void setPluralName(String pluralName);

    /**
     * Returns the iconPrefix
     *
     * @return The iconPrefix
     */
    public String getIconPrefix();

    /**
     * Sets the iconPrefix to given value
     *
     * @param iconPrefix
     *            The iconPrefix to set
     */
    public void setIconPrefix(String iconPrefix);

    /**
     * Returns the iconSuffix
     *
     * @return The iconSuffix
     */
    public String getIconSuffix();

    /**
     * Sets the iconSuffix to given value
     *
     * @param iconSuffix
     *            The iconSuffix to set
     */
    public void setIconSuffix(String iconSuffix);

    /**
     * Returns the id_foursquare
     *
     * @return The id_foursquare
     */
    public String getId_foursquare();

    /**
     * Sets the id_foursquare to given value
     *
     * @param id_foursquare
     *            The id_foursquare to set
     */
    public void setId_foursquare(String id_foursquare);

    /**
     * Returns the subCategories
     *
     * @return The subCategories
     */
    public List<JpaSubCategory> getSubCategories();

    /**
     * Sets the subCategories to given value
     *
     * @param subCategories
     *            The subCategories to set
     */
    public void setSubCategories(List<JpaSubCategory> subCategories);

}
