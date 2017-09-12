package es.udc.rdopazo.tfg.app.model.core.comentario;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.comentario.Comentario;

public interface ComentarioService<C extends Comentario> {

    public List<C> getAll();

    public C getById(Long id);

    public C add(C comentario);

    public C update(C comentario);

    public void delete(Long id);

}
