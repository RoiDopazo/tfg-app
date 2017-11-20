package es.udc.rdopazo.tfg.app.model.persistence.api.place;

import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public interface Place extends Entity<Long> {

    /**
     * Returns the name
     *
     * @return The name
     */
    String getName();

    /**
     * Sets the name to given value
     *
     * @param name
     *            The name to set
     */
    void setName(String name);

    /**
     * Returns the lat
     *
     * @return The lat
     */
    Double getLat();

    /**
     * Sets the lat to given value
     *
     * @param lat
     *            The lat to set
     */
    void setLat(Double lat);

    /**
     * Returns the lng
     *
     * @return The lng
     */
    Double getLng();

    /**
     * Sets the lng to given value
     *
     * @param lng
     *            The lng to set
     */
    void setLng(Double lng);

    /**
     * Returns the address
     *
     * @return The address
     */
    String getAddress();

    /**
     * Sets the address to given value
     *
     * @param address
     *            The address to set
     */
    void setAddress(String address);

    /**
     * Returns the cc
     *
     * @return The cc
     */
    String getCc();

    /**
     * Sets the cc to given value
     *
     * @param cc
     *            The cc to set
     */
    void setCc(String cc);

    /**
     * Returns the city
     *
     * @return The city
     */
    String getCity();

    /**
     * Sets the city to given value
     *
     * @param city
     *            The city to set
     */
    void setCity(String city);

    /**
     * Returns the country
     *
     * @return The country
     */
    String getCountry();

    /**
     * Sets the country to given value
     *
     * @param country
     *            The country to set
     */
    void setCountry(String country);

    /**
     * Returns the postalCode
     *
     * @return The postalCode
     */
    String getPostalCode();

    /**
     * Sets the postalCode to given value
     *
     * @param postalCode
     *            The postalCode to set
     */
    void setPostalCode(String postalCode);

    /**
     * Returns the province
     *
     * @return The province
     */
    String getProvince();

    /**
     * Sets the province to given value
     *
     * @param province
     *            The province to set
     */
    void setProvince(String province);

    /**
     * Returns the email
     *
     * @return The email
     */
    String getEmail();

    /**
     * Sets the email to given value
     *
     * @param email
     *            The email to set
     */
    void setEmail(String email);

    /**
     * Returns the facebook
     *
     * @return The facebook
     */
    String getFacebook();

    /**
     * Sets the facebook to given value
     *
     * @param facebook
     *            The facebook to set
     */
    void setFacebook(String facebook);

    /**
     * Returns the phone
     *
     * @return The phone
     */
    String getPhone();

    /**
     * Sets the phone to given value
     *
     * @param phone
     *            The phone to set
     */
    void setPhone(String phone);

    /**
     * Returns the twitter
     *
     * @return The twitter
     */
    String getTwitter();

    /**
     * Sets the twitter to given value
     *
     * @param twitter
     *            The twitter to set
     */
    void setTwitter(String twitter);

    /**
     * Returns the idFoursquare
     *
     * @return The idFoursquare
     */
    String getIdFoursquare();

    /**
     * Sets the idFoursquare to given value
     *
     * @param idFoursquare
     *            The idFoursquare to set
     */
    void setIdFoursquare(String idFoursquare);

    /**
     * Returns the verified
     *
     * @return The verified
     */
    Boolean getVerified();

    /**
     * Sets the verified to given value
     *
     * @param verified
     *            The verified to set
     */
    void setVerified(Boolean verified);

    /**
     * Returns the phto
     *
     * @return The phto
     */
    String getPhoto();

    /**
     * Sets the phto to given value
     *
     * @param phto
     *            The phto to set
     */
    void setPhoto(String photo);

    /**
     * Returns the categoryName
     *
     * @return The categoryName
     */
    String getCategoryName();

    /**
     * Sets the categoryName to given value
     *
     * @param categoryName
     *            The categoryName to set
     */
    void setCategoryName(String categoryName);

    /**
     * Returns the categoryIcon
     *
     * @return The categoryIcon
     */
    String getCategoryIcon();

    /**
     * Sets the categoryIcon to given value
     *
     * @param categoryIcon
     *            The categoryIcon to set
     */
    void setCategoryIcon(String categoryIcon);

}
