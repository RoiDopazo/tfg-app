package es.udc.rdopazo.tfg.service.api.place.dto;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class PlacePersistDto implements EntityDto {

    private Long id;

    private String name;

    private String photo;

    private Double lat;

    private Double lng;

    private String address;

    private String cc;

    private String city;

    private String country;

    private String postalCode;

    private String province;

    private String idFoursquare;

    private String categoryName;

    private String categoryIcon;

    private Boolean verified;

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
     * Returns the photo
     *
     * @return The photo
     */
    public String getPhoto() {
        return this.photo;
    }

    /**
     * Sets the photo to given value
     *
     * @param photo
     *            The photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Returns the lat
     *
     * @return The lat
     */
    public Double getLat() {
        return this.lat;
    }

    /**
     * Sets the lat to given value
     *
     * @param lat
     *            The lat to set
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * Returns the lng
     *
     * @return The lng
     */
    public Double getLng() {
        return this.lng;
    }

    /**
     * Sets the lng to given value
     *
     * @param lng
     *            The lng to set
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    /**
     * Returns the address
     *
     * @return The address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets the address to given value
     *
     * @param address
     *            The address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the cc
     *
     * @return The cc
     */
    public String getCc() {
        return this.cc;
    }

    /**
     * Sets the cc to given value
     *
     * @param cc
     *            The cc to set
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * Returns the city
     *
     * @return The city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Sets the city to given value
     *
     * @param city
     *            The city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the country
     *
     * @return The country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Sets the country to given value
     *
     * @param country
     *            The country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the postalCode
     *
     * @return The postalCode
     */
    public String getPostalCode() {
        return this.postalCode;
    }

    /**
     * Sets the postalCode to given value
     *
     * @param postalCode
     *            The postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Returns the province
     *
     * @return The province
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * Sets the province to given value
     *
     * @param province
     *            The province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Returns the idFoursquare
     *
     * @return The idFoursquare
     */
    public String getIdFoursquare() {
        return this.idFoursquare;
    }

    /**
     * Sets the idFoursquare to given value
     *
     * @param idFoursquare
     *            The idFoursquare to set
     */
    public void setIdFoursquare(String idFoursquare) {
        this.idFoursquare = idFoursquare;
    }

    /**
     * Returns the categoryName
     *
     * @return The categoryName
     */
    public String getCategoryName() {
        return this.categoryName;
    }

    /**
     * Sets the categoryName to given value
     *
     * @param categoryName
     *            The categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Returns the categoryIcon
     *
     * @return The categoryIcon
     */
    public String getCategoryIcon() {
        return this.categoryIcon;
    }

    /**
     * Sets the categoryIcon to given value
     *
     * @param categoryIcon
     *            The categoryIcon to set
     */
    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    /**
     * Returns the verified
     *
     * @return The verified
     */
    public Boolean getVerified() {
        return this.verified;
    }

    /**
     * Sets the verified to given value
     *
     * @param verified
     *            The verified to set
     */
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

}
