package es.udc.rdopazo.tfg.app.model.persistence.jpa.comentario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.comentario.Comentario;

@Entity
@Table(name = "PLACE_COMMENT")
@SequenceGenerator(name = "place_comment_seq", sequenceName = "PLACE_COMMENT_SEQ", allocationSize = 1)
public class JpaComentario implements Comentario {

    private static final long serialVersionUID = -3744362659781058549L;

    @Id
    @Column(name = "X_COMM")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_comment_seq")
    private Long id;

    @Column(name = "TITLE")
    private String titulo;

    @Column(name = "BODY")
    private String cuerpo;

    @Column(name = "STATE")
    private String estado;

    @Column(name = "REASON")
    private String razon;

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
     * Returns the titulo
     *
     * @return The titulo
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Sets the titulo to given value
     *
     * @param titulo
     *            The titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Returns the cuerpo
     *
     * @return The cuerpo
     */
    public String getCuerpo() {
        return this.cuerpo;
    }

    /**
     * Sets the cuerpo to given value
     *
     * @param cuerpo
     *            The cuerpo to set
     */
    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    /**
     * Returns the estado
     *
     * @return The estado
     */
    public String getEstado() {
        return this.estado;
    }

    /**
     * Sets the estado to given value
     *
     * @param estado
     *            The estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Returns the razon
     *
     * @return The razon
     */
    public String getRazon() {
        return this.razon;
    }

    /**
     * Sets the razon to given value
     *
     * @param razon
     *            The razon to set
     */
    public void setRazon(String razon) {
        this.razon = razon;
    }

}
