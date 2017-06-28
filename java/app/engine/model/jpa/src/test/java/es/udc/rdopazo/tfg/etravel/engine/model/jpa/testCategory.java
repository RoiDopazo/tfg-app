package es.udc.rdopazo.tfg.etravel.engine.model.jpa;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.udc.rdopazo.tfg.etravel.engine.model.jpa.category.JpaCategory;
import es.udc.rdopazo.tfg.etravel.engine.model.jpa.category.dao.JpaCategoryDao;

public class testCategory extends BaseTest {

    @Autowired
    JpaCategoryDao dao;

    @Test
    @Transactional
    public void test() {
        List<JpaCategory> x = this.dao.getAll();
        System.out.println(x.get(0).getName());

    }

}
