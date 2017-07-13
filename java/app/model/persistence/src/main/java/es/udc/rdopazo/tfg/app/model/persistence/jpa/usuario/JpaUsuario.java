package es.udc.rdopazo.tfg.app.model.persistence.jpa.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;

@Entity
@Table(name = "USER_NORMAL")
@SequenceGenerator(name = "user_normal_seq", sequenceName = "USER_NORMAL_SEC", allocationSize = 1)
public class JpaUsuario implements Usuario {

    private static final long serialVersionUID = -6922291204813311075L;

    @Id
    @Column(name = "X_USER")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_normal_seq")
    private Long id;

    @Column(name = "NAME")
    private String nombre;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

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

}
