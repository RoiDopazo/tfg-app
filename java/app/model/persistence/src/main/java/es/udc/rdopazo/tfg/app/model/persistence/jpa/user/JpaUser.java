package es.udc.rdopazo.tfg.app.model.persistence.jpa.user;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.JpaRoute;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.Role;

@Entity
@Table(name = "USER_")
@SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQ", allocationSize = 1)
public class JpaUser implements Usuario {

    private static final long serialVersionUID = -6922291204813311075L;

    @Id
    @Column(name = "X_USER")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<JpaRoute> routes;

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
     * Returns the username
     *
     * @return The username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username to given value
     *
     * @param nombre
     *            The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password
     *
     * @return The password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password to given value
     *
     * @param password
     *            The password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Returns the creationDate
     *
     * @return The creationDate
     */
    public Date getCreationDate() {
        return this.creationDate;
    }

    /**
     * Sets the creationDate to given value
     *
     * @param creationDate
     *            The creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Returns the token
     *
     * @return The token
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Sets the token to given value
     *
     * @param token
     *            The token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Returns the role
     *
     * @return The role
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Sets the role to given value
     *
     * @param role
     *            The role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

}
