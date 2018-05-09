package es.udc.rdopazo.tfg.app.model.core.place;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.udc.rdopazo.tfg.app.model.core.BaseTest;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.dao.PlaceDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.place.JpaPlace;

public class PlaceServiceTest<P extends Place> extends BaseTest {

    @Autowired
    private PlaceService<P> placeService;

    @Autowired
    private PlaceDao<P> placeDao;

    private Long insertValidPlace(String idFs) {
        P place = (P) new JpaPlace();
        place.setAddress("AAA");
        place.setCategoryIcon("BBB");
        place.setCategoryName("CCC");
        place.setCc("DDD");
        place.setCity("EEE");
        place.setCountry("FFF");
        place.setEmail("GGG");
        place.setFacebook("HHH");
        place.setIdFoursquare(idFs);
        place.setLat(1.11);
        place.setLng(2.22);
        place.setName("JJJ");
        place.setPhone("KKK");
        place.setVerified(false);
        return this.placeService.add(place).getId();
    }

    @Transactional
    @Test
    public void testGetAll() {
        try {
            Long idPlace1 = this.insertValidPlace("1111");
            Long idPlace2 = this.insertValidPlace("2222");
            List<P> places = this.placeService.getAll();
            assertEquals(places.size(), 2);
        } finally {
            this.placeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetById() {
        try {
            Long idPlace1 = this.insertValidPlace("1111");
            P place = this.placeService.getById(idPlace1);
            assertEquals(place.getId(), idPlace1);
        } finally {
            this.placeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testAdd() {
        try {
            Long idPlace1 = this.insertValidPlace("1111");
            P place = this.placeService.getById(idPlace1);
            assertEquals(place.getId(), idPlace1);
        } finally {
            this.placeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testUpdate() {
        try {
            Long idPlace1 = this.insertValidPlace("1111");
            P place = this.placeService.getById(idPlace1);
            place.setIdFoursquare("0000");
            place = this.placeService.update(place);
            assertEquals(place.getIdFoursquare(), "0000");
        } finally {
            this.placeDao.clearTable();
        }

    }

    @Transactional
    @Test
    public void testDelete() {
        try {
            Long idPlace1 = this.insertValidPlace("1111");
            this.placeService.delete(idPlace1);
            List<P> places = this.placeService.getAll();
            assertEquals(places.size(), 0);
        } finally {
            this.placeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetListByField() {
        try {
            Long idPlace1 = this.insertValidPlace("1111");
            Long idPlace2 = this.insertValidPlace("2222");
            List<P> places = this.placeService.getListByField("idFoursquare", "2222", null, null);
            assertEquals(places.size(), 1);
            assertEquals(places.get(0).getIdFoursquare(), "2222");

            places = this.placeService.getListByField("verified", "false", null, null);
            assertEquals(places.size(), 2);

            places = this.placeService.getListByField("", "", null, null);
            assertEquals(places.size(), 2);

            places = this.placeService.getListByField("verified", "null", null, null);
            assertEquals(places.size(), 2);

            places = this.placeService.getListByField("ppp", "", null, null);
            assertEquals(places.size(), 2);

            places = this.placeService.getListByField("", "ppp", null, null);
            assertEquals(places.size(), 2);

        } finally {
            this.placeDao.clearTable();
        }
    }

}
