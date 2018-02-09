package es.udc.rdopazo.tfg.app.model.persistence.jpa.subcategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.category.JpaCategory;

@Entity
@Table(name = "CATEGORY_SUB")
@SequenceGenerator(name = "category_sub_seq", sequenceName = "CATEGORY_SUB_SEQ", allocationSize = 1)
public class JpaSubCategory implements SubCategory<JpaCategory> {

    private static final long serialVersionUID = -2896345425986479754L;

    @Id
    @Column(name = "X_SUBC")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sub_seq")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PLURAL_NAME")
    private String pluralName;

    @Column(name = "ICON_PREFIX")
    private String iconPrefix;

    @Column(name = "ICON_SUFFIX")
    private String iconSuffix;

    @Column(name = "ID_FOURSQUARE")
    private String id_foursquare;

    @ManyToOne
    @JoinColumn(name = "CAT_X_CAT")
    private JpaCategory category;

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
     * Returns the category
     *
     * @return The category
     */
    public JpaCategory getCategory() {
        return this.category;
    }

    /**
     * Sets the category to given value
     *
     * @param category
     *            The category to set
     */
    public void setCategory(JpaCategory category) {
        this.category = category;
    }

}
