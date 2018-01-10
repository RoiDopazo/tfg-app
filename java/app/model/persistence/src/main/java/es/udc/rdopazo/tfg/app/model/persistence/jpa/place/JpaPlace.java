package es.udc.rdopazo.tfg.app.model.persistence.jpa.place;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.JpaStay;

@Entity
@Table(name = "PLACE")
@SequenceGenerator(name = "place_seq", sequenceName = "PLACE_SEQ", allocationSize = 1)
public class JpaPlace implements Place {

    private static final long serialVersionUID = 5215196298204512256L;

    @Id
    @Column(name = "X_PLACE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_seq")
    private Long id;

    @OneToMany(mappedBy = "place", fetch = FetchType.EAGER)
    private List<JpaStay> stays;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHOTO")
    private String photo;

    @Column(name = "LAT")
    private Double lat;

    @Column(name = "LNG")
    private Double lng;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CC")
    private String cc;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FACEBOOK")
    private String facebook;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "TWITTER")
    private String twitter;

    @Column(name = "ID_FOURSQUARE")
    private String idFoursquare;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @Column(name = "CATEGORY_ICON")
    private String categoryIcon;

    @Column(name = "VERIFIED")
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
     * Returns the email
     *
     * @return The email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email to given value
     *
     * @param email
     *            The email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the facebook
     *
     * @return The facebook
     */
    public String getFacebook() {
        return this.facebook;
    }

    /**
     * Sets the facebook to given value
     *
     * @param facebook
     *            The facebook to set
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     * Returns the phone
     *
     * @return The phone
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Sets the phone to given value
     *
     * @param phone
     *            The phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the twitter
     *
     * @return The twitter
     */
    public String getTwitter() {
        return this.twitter;
    }

    /**
     * Sets the twitter to given value
     *
     * @param twitter
     *            The twitter to set
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
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

    /**
     * Returns the dayPlaces
     *
     * @return The dayPlaces
     */
    public List<JpaStay> getStays() {
        return this.stays;
    }

    /**
     * Sets the dayPlaces to given value
     *
     * @param dayPlaces
     *            The dayPlaces to set
     */
    public void setStays(List<JpaStay> stays) {
        this.stays = stays;
    }

    /**
     * Returns the phto
     *
     * @return The phto
     */
    public String getPhoto() {
        return this.photo;
    }

    /**
     * Sets the phto to given value
     *
     * @param phto
     *            The phto to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
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

    // @OneToMany(fetch = FetchType.EAGER, mappedBy = "lugar")
    // private List<JpaRutaLugar> ruta_luagres;

}