package es.udc.rdopazo.tfg.app.model.persistence.api.categoria.dao;

import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;
import es.udc.rdopazo.tfg.app.model.persistence.util.DaoSupport;

public interface CategoriaDao<C extends Categoria> extends DaoSupport<Long, C> {

}
