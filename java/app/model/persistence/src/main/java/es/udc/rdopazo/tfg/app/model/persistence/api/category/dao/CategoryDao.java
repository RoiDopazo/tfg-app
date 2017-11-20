package es.udc.rdopazo.tfg.app.model.persistence.api.category.dao;

import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.model.persistence.util.DaoSupport;

public interface CategoryDao<C extends Category> extends DaoSupport<Long, C> {

}
