package es.udc.rdopazo.tfg.app.model.core.category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.udc.rdopazo.tfg.app.model.core.BaseTest;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.dao.CategoryDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.dao.SubCategoryDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.category.JpaCategory;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.subcategory.JpaSubCategory;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public class CategoryServiceTest<C extends Category, S extends SubCategory<C>> extends BaseTest {

    @Autowired
    private CategoryService<C, S> service;

    @Autowired
    private CategoryDao<C> catDao;

    @Autowired
    private SubCategoryDao<S> subCatDao;

    private Long insertValidCategory(String idFs) {
        C cat = (C) new JpaCategory();
        cat.setIconPrefix("AAA");
        cat.setIconSuffix("BBB");
        cat.setId_foursquare(idFs);
        cat.setName("CCC");
        cat.setPluralName("DDD");

        return this.service.addCategory(cat).getId();
    }

    private Long insertValidSubCategory(C category, String idFs) {
        S subcat = (S) new JpaSubCategory();
        subcat.setIconPrefix("AAA");
        subcat.setIconSuffix("BBB");
        subcat.setId_foursquare(idFs);
        subcat.setName("CCC");
        subcat.setPluralName("DDD");
        subcat.setCategory(category);

        return this.service.addSubCategory(subcat).getId();
    }

    @Transactional
    @Test
    public void testGetAllCategories() {
        try {
            Long cat1 = this.insertValidCategory("1111");
            Long cat2 = this.insertValidCategory("2222");
            List<C> categories = this.service.getAllCategories();
            assertEquals(categories.size(), 2);
        } finally {
            this.catDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetCategoryById() {
        Boolean exc = false;
        try {
            Long cat1 = this.insertValidCategory("1111");
            Long cat2 = this.insertValidCategory("2222");
            try {
                C category = this.service.getCategoryById(cat1);
                assertEquals(category.getId(), cat1);
                assertEquals(category.getId_foursquare(), "1111");
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

            try {
                C category = this.service.getCategoryById(0L);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertTrue(exc);

        } finally {
            this.catDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testAddCategory() {
        Boolean exc = false;
        try {
            Long cat1 = this.insertValidCategory("1111");
            try {
                C cat = this.service.getCategoryById(cat1);
                assertEquals(cat.getId(), cat1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

        } finally {
            this.catDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testUpdateCategory() {
        Boolean exc = false;
        try {
            Long cat1 = this.insertValidCategory("1111");
            try {
                C cat = this.service.getCategoryById(cat1);
                cat.setIconPrefix("XXXXXXX");
                cat = this.service.updateCategory(cat);
                assertEquals(cat.getIconPrefix(), "XXXXXXX");
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

        } finally {
            this.catDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testDeleteCategory() {
        Boolean exc = false;
        try {
            Long cat1 = this.insertValidCategory("1111");
            try {
                this.service.deleteCategory(cat1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

            try {
                this.service.deleteCategory(0L);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertTrue(exc);

        } finally {
            this.catDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetCategoriesListByField() {
        Boolean exc = false;
        try {
            Long cat1 = this.insertValidCategory("1111");
            Long cat2 = this.insertValidCategory("2222");
            List<C> categories = this.service.getCategoriesListByField("id_foursquare", "1111", null, null);
            assertEquals(categories.size(), 1);
            assertEquals(categories.get(0).getId_foursquare(), "1111");

            categories = this.service.getCategoriesListByField("", "", null, null);
            assertEquals(categories.size(), 2);

            categories = this.service.getCategoriesListByField("", "1111", null, null);
            assertEquals(categories.size(), 2);

            categories = this.service.getCategoriesListByField("id_foursquare", "", null, null);
            assertEquals(categories.size(), 2);

        } finally {
            this.catDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetAllSubCategories() throws InstanceNotFoundException {
        try {
            Long cat1 = this.insertValidCategory("1111");
            Long subcat1 = this.insertValidSubCategory(this.service.getCategoryById(cat1), "AAAA");
            Long subcat2 = this.insertValidSubCategory(this.service.getCategoryById(cat1), "BBBB");
            List<S> subcategories = this.service.getAllSubCategories();
            assertEquals(subcategories.size(), 2);
            assertEquals(subcategories.get(0).getId(), subcat1);
        } finally {
            this.subCatDao.clearTable();
            this.catDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetSubCategoryById() throws InstanceNotFoundException {
        Boolean exc = false;
        try {
            Long cat1 = this.insertValidCategory("1111");
            Long subcat1 = this.insertValidSubCategory(this.service.getCategoryById(cat1), "AAAA");
            Long subcat2 = this.insertValidSubCategory(this.service.getCategoryById(cat1), "BBBB");
            try {
                S subcategory = this.service.getSubCategoryById(subcat2);
                assertEquals(subcategory.getId(), subcat2);
                assertEquals(subcategory.getId_foursquare(), "BBBB");
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

            try {
                S subcategory = this.service.getSubCategoryById(0L);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertTrue(exc);

        } finally {
            this.subCatDao.clearTable();
            this.catDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testAddSubCategory() throws InstanceNotFoundException {
        Boolean exc = false;
        try {
            Long cat1 = this.insertValidCategory("1111");
            Long subcat1 = this.insertValidSubCategory(this.service.getCategoryById(cat1), "AAAA");
            try {
                S subcat = this.service.getSubCategoryById(subcat1);
                assertEquals(subcat.getId(), subcat1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

        } finally {
            this.subCatDao.clearTable();
            this.catDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testUpdateSubCategory() throws InstanceNotFoundException {
        Boolean exc = false;
        try {
            Long cat1 = this.insertValidCategory("1111");
            Long subcat1 = this.insertValidSubCategory(this.service.getCategoryById(cat1), "AAAA");
            try {
                S subcat = this.service.getSubCategoryById(subcat1);
                subcat.setIconPrefix("XXXXXXX");
                subcat = this.service.updateSubCategory(subcat);
                assertEquals(subcat.getIconPrefix(), "XXXXXXX");
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

        } finally {
            this.subCatDao.clearTable();
            this.catDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testDeleteSubCategory() throws InstanceNotFoundException {
        Boolean exc = false;
        try {
            Long cat1 = this.insertValidCategory("1111");
            Long subcat1 = this.insertValidSubCategory(this.service.getCategoryById(cat1), "AAAA");
            try {
                this.service.deleteSubCategory(subcat1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

            try {
                this.service.deleteSubCategory(0L);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertTrue(exc);

        } finally {
            this.subCatDao.clearTable();
            this.catDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetSubCategoriesByFields() throws InstanceNotFoundException {
        Boolean exc = false;
        try {
            Long cat1 = this.insertValidCategory("1111");
            Long subcat1 = this.insertValidSubCategory(this.service.getCategoryById(cat1), "AAAA");
            Long subcat2 = this.insertValidSubCategory(this.service.getCategoryById(cat1), "BBBB");
            List<S> subcategories = this.service.getSubCategoriesByFields(cat1, "id_foursquare", "AAAA", null, null);
            assertEquals(subcategories.size(), 1);
            assertEquals(subcategories.get(0).getId_foursquare(), "AAAA");

            subcategories = this.service.getSubCategoriesByFields(cat1, "", "", null, null);
            assertEquals(subcategories.size(), 2);

            subcategories = this.service.getSubCategoriesByFields(null, "", "1111", null, null);
            assertEquals(subcategories.size(), 2);

            subcategories = this.service.getSubCategoriesByFields(cat1, "id_foursquare", "", null, null);
            assertEquals(subcategories.size(), 2);

        } finally {
            this.subCatDao.clearTable();
            this.catDao.clearTable();
        }
    }

}
