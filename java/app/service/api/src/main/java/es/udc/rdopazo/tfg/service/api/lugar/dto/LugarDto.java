package es.udc.rdopazo.tfg.service.api.lugar.dto;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class LugarDto implements EntityDto {

    private static final long serialVersionUID = 2165236292757330644L;

    private Long id;

    private String name;

    private Double lat;

    private Double lng;

    private String address;

    private String cc;

    private String city;

    private String country;

    private String postalCode;

    private String province;

    private String email;

    private String facebook;

    private String phone;

    private String twitter;

    private String idFoursquare;

    private Boolean verified;

    private String foto;

    private String categoriaIcon;

    private String categoriaName;

    private Long numeroLikes;

    private Boolean[] condListDays;

    private Integer numDaysAsigned;

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
     * Returns the foto
     *
     * @return The foto
     */
    public String getFoto() {
        return this.foto;
    }

    /**
     * Sets the foto to given value
     *
     * @param foto
     *            The foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Returns the categoriaIcon
     *
     * @return The categoriaIcon
     */
    public String getCategoriaIcon() {
        return this.categoriaIcon;
    }

    /**
     * Sets the categoriaIcon to given value
     *
     * @param categoriaIcon
     *            The categoriaIcon to set
     */
    public void setCategoriaIcon(String categoriaIcon) {
        this.categoriaIcon = categoriaIcon;
    }

    /**
     * Returns the categoriaName
     *
     * @return The categoriaName
     */
    public String getCategoriaName() {
        return this.categoriaName;
    }

    /**
     * Sets the categoriaName to given value
     *
     * @param categoriaName
     *            The categoriaName to set
     */
    public void setCategoriaName(String categoriaName) {
        this.categoriaName = categoriaName;
    }

    /**
     * Returns the numeroLikes
     *
     * @return The numeroLikes
     */
    public Long getNumeroLikes() {
        return this.numeroLikes;
    }

    /**
     * Sets the numeroLikes to given value
     *
     * @param numeroLikes
     *            The numeroLikes to set
     */
    public void setNumeroLikes(Long numeroLikes) {
        this.numeroLikes = numeroLikes;
    }

    /**
     * Returns the condListDays
     * 
     * @return The condListDays
     */
    public Boolean[] getCondListDays() {
        return this.condListDays;
    }

    /**
     * Sets the condListDays to given value
     * 
     * @param condListDays
     *            The condListDays to set
     */
    public void setCondListDays(Boolean[] condListDays) {
        this.condListDays = condListDays;
    }

    /**
     * Returns the numDaysAsigned
     * 
     * @return The numDaysAsigned
     */
    public Integer getNumDaysAsigned() {
        return this.numDaysAsigned;
    }

    /**
     * Sets the numDaysAsigned to given value
     * 
     * @param numDaysAsigned
     *            The numDaysAsigned to set
     */
    public void setNumDaysAsigned(Integer numDaysAsigned) {
        this.numDaysAsigned = numDaysAsigned;
    }

}
