package es.udc.rdopazo.tfg.app.model.core.subcategory.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.subcategory.SubCategoryService;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.dao.SubCategoryDao;

@Service
public class SubCategoryServiceImpl<S extends SubCategory<?>> implements SubCategoryService<S> {

    @Autowired
    public SubCategoryDao<S> dao;

	public List<S> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public S getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public S add(S subCategoria) {
		// TODO Auto-generated method stub
		return null;
	}

	public S update(S subCategoria) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
