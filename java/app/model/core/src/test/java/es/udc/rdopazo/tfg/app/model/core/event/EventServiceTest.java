package es.udc.rdopazo.tfg.app.model.core.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.udc.rdopazo.tfg.app.model.core.BaseTest;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.dao.EventDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.dao.EventDayDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.dao.EventPlaceDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.JpaEvent;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.JpaEventDay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.place.JpaEventPlace;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public class EventServiceTest<E extends Event<ED>, ED extends EventDay<E, EP>, EP extends EventPlace<ED>>
        extends BaseTest {

    @Autowired
    private EventService<E, ED, EP> service;

    @Autowired
    private EventDao<E> eventDao;

    @Autowired
    private EventDayDao<ED> eventDayDao;

    @Autowired
    private EventPlaceDao<EP> eventPlaceDao;

    private Long insertValidEvent() {
        E event = (E) new JpaEvent();
        event.setCity("AAA");
        event.setDescription("BBB");
        event.setName("CCC");

        return this.service.addEvent(event).getId();
    }

    private Long insertValidEventDay(E event) throws InstanceNotFoundException {
        ED eventDay = (ED) new JpaEventDay();
        eventDay.setDate(new Date());
        eventDay.setNumEvPlaces(0);
        eventDay.setEvent(event);

        return this.service.addEventDay(event.getId(), eventDay).getDayPK().getIdDay();
    }

    private Long insertValidEventPlace(ED eventDay) throws InstanceNotFoundException {
        EP eventPlace = (EP) new JpaEventPlace();
        eventPlace.setAddress("AAA");
        eventPlace.setDescription("BBB");
        eventPlace.setEndHour(0L);
        eventPlace.setLat(1.11);
        eventPlace.setLng(2.22);
        eventPlace.setName("CCC");
        eventPlace.setStartHour(0L);
        eventPlace.setDay(eventDay);

        return this.service.addEventPlace(eventDay.getDayPK().getIdEvent(), eventDay.getDayPK().getIdDay(), eventPlace)
                .getId();
    }

    @Transactional
    @Test
    public void testGetAllEvents() {
        try {
            Long event1 = this.insertValidEvent();
            Long event2 = this.insertValidEvent();
            List<E> events = this.service.getAllEvents(null, null);
            assertEquals(events.size(), 2);

            events = this.service.getAllEvents(0, 1);
            assertEquals(events.size(), 1);
        } finally {
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetEventsByField() {
        try {
            Long event1 = this.insertValidEvent();
            Long event2 = this.insertValidEvent();
            List<E> events = this.service.getEventsByField("city", "AAA", null, null);
            assertEquals(events.size(), 2);
            assertEquals(events.get(0).getCity(), "AAA");

            events = this.service.getEventsByField("", "", null, null);
            assertEquals(events.size(), 2);

            events = this.service.getEventsByField("", "1111", null, null);
            assertEquals(events.size(), 2);

            events = this.service.getEventsByField("city", "", null, null);
            assertEquals(events.size(), 2);

        } finally {
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetEventById() {
        Boolean exc = false;
        try {
            Long event1 = this.insertValidEvent();
            Long event2 = this.insertValidEvent();
            try {
                E event = this.service.getEventById(event2);
                assertEquals(event.getId(), event2);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

            try {
                E event = this.service.getEventById(0L);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }

            assertTrue(exc);

        } finally {
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testAddEvent() {
        Boolean exc = false;
        try {
            Long event1 = this.insertValidEvent();
            try {
                E event = this.service.getEventById(event1);
                assertEquals(event.getId(), event1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

        } finally {
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testUpdateEvent() {
        Boolean exc = false;
        try {
            Long event1 = this.insertValidEvent();
            try {
                E event = this.service.getEventById(event1);
                event.setCity("XXXXXXX");
                event = this.service.updateEvent(event);
                assertEquals(event.getCity(), "XXXXXXX");
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

        } finally {
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testDeleteEvent() {
        Boolean exc = false;
        try {
            Long event1 = this.insertValidEvent();
            try {
                this.service.deleteEvent(event1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

            try {
                this.service.deleteEvent(event1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertTrue(exc);

        } finally {
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testAddEventDay() throws InstanceNotFoundException {
        Boolean exc = false;
        try {
            Long event1 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            try {
                ED eventday = this.service.getEventDayById(event1, day1);
                assertEquals(eventday.getDayPK().getIdEvent(), event1);
                assertEquals(eventday.getDayPK().getIdDay(), day1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

        } finally {
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testUpdateEventDay() throws InstanceNotFoundException {
        Boolean exc = false;
        try {
            Long event1 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            try {
                ED eventday = this.service.getEventDayById(event1, day1);
                eventday.setDate(null);
                eventday = this.service.updateEventDay(eventday);
                assertEquals(eventday.getDate(), null);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

        } finally {
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testDeleteEventDay() throws InstanceNotFoundException {
        Boolean exc = false;
        try {
            Long event1 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            try {
                this.service.deleteEventDay(event1, day1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

            try {
                this.service.deleteEventDay(event1, day1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertTrue(exc);

        } finally {
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetAllEventDaysLongIntegerInteger() throws InstanceNotFoundException {
        try {
            Long event1 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            Long day2 = this.insertValidEventDay(this.service.getEventById(event1));
            List<ED> eventdays = this.service.getAllEventDays(event1, null, null);
            assertEquals(eventdays.size(), 2);

            eventdays = this.service.getAllEventDays(event1, 0, 1);
            assertEquals(eventdays.size(), 1);
        } finally {
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetEventDaysByField() throws InstanceNotFoundException {
        try {
            Long event1 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            Long day2 = this.insertValidEventDay(this.service.getEventById(event1));
            List<ED> eventdays = this.service.getEventDaysByField("numEvPlaces", 0, null, null);
            assertEquals(eventdays.size(), 2);

            eventdays = this.service.getEventDaysByField("", "", null, null);
            assertEquals(eventdays.size(), 2);

            eventdays = this.service.getEventDaysByField("", "1111", null, null);
            assertEquals(eventdays.size(), 2);

            eventdays = this.service.getEventDaysByField("city", "", null, null);
            assertEquals(eventdays.size(), 2);

        } finally {
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetEventDaysByFields() throws InstanceNotFoundException {
        try {
            Long event1 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            Long day2 = this.insertValidEventDay(this.service.getEventById(event1));
            List<ED> eventdays = this.service.getEventDaysByFields(event1, day1, "numEvPlaces", 0, null, null);
            assertEquals(eventdays.size(), 1);

            eventdays = this.service.getEventDaysByFields(event1, day1, "numEvPlaces", 10, null, null);
            assertEquals(eventdays.size(), 0);

            eventdays = this.service.getEventDaysByFields(event1, null, "numEvPlaces", 0, null, null);
            assertEquals(eventdays.size(), 2);

            eventdays = this.service.getEventDaysByFields(null, null, "", "1111", null, null);
            assertEquals(eventdays.size(), 2);

            eventdays = this.service.getEventDaysByFields(null, null, "asds", "", null, null);
            assertEquals(eventdays.size(), 2);

        } finally {
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetEventDayById() throws InstanceNotFoundException {
        Boolean exc = false;
        try {
            Long event1 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            Long day2 = this.insertValidEventDay(this.service.getEventById(event1));
            try {
                ED eventday = this.service.getEventDayById(event1, day1);
                assertEquals(eventday.getDayPK().getIdEvent(), event1);
                assertEquals(eventday.getDayPK().getIdDay(), day1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

            try {
                ED eventday = this.service.getEventDayById(0L, 0L);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }

            assertTrue(exc);

        } finally {
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetAllEventDaysIntegerInteger() throws InstanceNotFoundException {
        try {
            Long event1 = this.insertValidEvent();
            Long event2 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            Long day2 = this.insertValidEventDay(this.service.getEventById(event1));
            Long day3 = this.insertValidEventDay(this.service.getEventById(event2));
            List<ED> eventdays = this.service.getAllEventDays(null, null);
            assertEquals(eventdays.size(), 3);

            eventdays = this.service.getAllEventDays(0, 1);
            assertEquals(eventdays.size(), 1);
        } finally {
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetAllEventDaysByDateRange() throws InstanceNotFoundException {
        try {
            Long event1 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            Long day2 = this.insertValidEventDay(this.service.getEventById(event1));

            List<ED> eventdays = this.service.getAllEventDaysByDateRange("AAA", new Date(), new Date(), "BETWEEN", null,
                    null);
            assertEquals(eventdays.size(), 0);

            eventdays = this.service.getAllEventDaysByDateRange("AAAA", new Date(), new Date(), "BETWEEN", null, null);
            assertEquals(eventdays.size(), 0);

            eventdays = this.service.getAllEventDaysByDateRange("AAA", new Date(), new Date(), "OVER", null, null);
            assertEquals(eventdays.size(), 0);

            eventdays = this.service.getAllEventDaysByDateRange("AAA", null, null, "OVER", null, null);
            assertEquals(eventdays, null);

            eventdays = this.service.getAllEventDaysByDateRange("AAA", new Date(), null, "OVER", null, null);
            assertEquals(eventdays, null);

        } finally {
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetAllEventPlaces() throws InstanceNotFoundException {
        try {
            Long event1 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            Long day2 = this.insertValidEventDay(this.service.getEventById(event1));
            Long eventplace1 = this.insertValidEventPlace(this.service.getEventDayById(event1, day1));
            Long eventplace2 = this.insertValidEventPlace(this.service.getEventDayById(event1, day1));
            Long eventplace3 = this.insertValidEventPlace(this.service.getEventDayById(event1, day2));

            List<EP> eventplaces = this.service.getAllEventPlaces(null, null);

            assertEquals(eventplaces.size(), 3);
            eventplaces = this.service.getAllEventPlaces(0, 1);
            assertEquals(eventplaces.size(), 1);
        } finally {
            this.eventPlaceDao.clearTable();
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetEventPlaceById() throws InstanceNotFoundException {
        Boolean exc = false;
        try {
            Long event1 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            Long day2 = this.insertValidEventDay(this.service.getEventById(event1));
            Long eventplace1 = this.insertValidEventPlace(this.service.getEventDayById(event1, day1));
            Long eventplace2 = this.insertValidEventPlace(this.service.getEventDayById(event1, day1));
            Long eventplace3 = this.insertValidEventPlace(this.service.getEventDayById(event1, day2));
            try {
                EP eventplace = this.service.getEventPlaceById(eventplace1);
                assertEquals(eventplace.getId(), eventplace1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

            try {
                EP eventplace = this.service.getEventPlaceById(0L);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }

            assertTrue(exc);

        } finally {
            this.eventPlaceDao.clearTable();
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testAddEventPlace() throws InstanceNotFoundException {
        Boolean exc = false;
        try {
            Long event1 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            Long eventplace1 = this.insertValidEventPlace(this.service.getEventDayById(event1, day1));
            EP eventPlace = (EP) new JpaEventPlace();
            try {
                EP eventplace = this.service.getEventPlaceById(eventplace1);
                assertEquals(eventplace.getId(), eventplace1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

            try {
                EP eventplace = this.service.addEventPlace(null, null, eventPlace);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertTrue(exc);

        } finally {
            this.eventPlaceDao.clearTable();
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testUpdateEventPlace() throws InstanceNotFoundException {
        Boolean exc = false;
        try {
            Long event1 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            Long eventplace1 = this.insertValidEventPlace(this.service.getEventDayById(event1, day1));
            try {
                EP eventplace = this.service.getEventPlaceById(eventplace1);
                eventplace.setDescription("XXXXXXX");
                eventplace = this.service.updateEventPlace(eventplace);
                assertEquals(eventplace.getDescription(), "XXXXXXX");
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

        } finally {
            this.eventPlaceDao.clearTable();
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testDeleteEventPlace() throws InstanceNotFoundException {
        Boolean exc = false;
        try {
            Long event1 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            Long eventplace1 = this.insertValidEventPlace(this.service.getEventDayById(event1, day1));
            try {
                this.service.deleteEventPlace(eventplace1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertFalse(exc);

            try {
                this.service.deleteEventPlace(eventplace1);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }
            assertTrue(exc);

        } finally {
            this.eventPlaceDao.clearTable();
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetEventPlacesByFields() throws InstanceNotFoundException {
        try {
            Long event1 = this.insertValidEvent();
            Long day1 = this.insertValidEventDay(this.service.getEventById(event1));
            Long day2 = this.insertValidEventDay(this.service.getEventById(event1));
            Long eventplace1 = this.insertValidEventPlace(this.service.getEventDayById(event1, day1));
            Long eventplace2 = this.insertValidEventPlace(this.service.getEventDayById(event1, day1));
            Long eventplace3 = this.insertValidEventPlace(this.service.getEventDayById(event1, day2));

            List<EP> eventplaces = this.service.getEventPlacesByFields(event1, day1, "lat", 1.11, null, null);
            assertEquals(eventplaces.size(), 2);

            eventplaces = this.service.getEventPlacesByFields(event1, null, "lat", 1.11, null, null);
            assertEquals(eventplaces.size(), 3);

            eventplaces = this.service.getEventPlacesByFields(null, null, "lat", 1.11, null, null);
            assertEquals(eventplaces.size(), 3);

            eventplaces = this.service.getEventPlacesByFields(null, null, "lat", 1.1111, null, null);
            assertEquals(eventplaces.size(), 0);

            eventplaces = this.service.getEventPlacesByFields(null, null, "", "1111", null, null);
            assertEquals(eventplaces.size(), 3);

            eventplaces = this.service.getEventPlacesByFields(null, null, "", "", null, null);
            assertEquals(eventplaces.size(), 3);

            eventplaces = this.service.getEventPlacesByFields(null, null, "asds", "", null, null);
            assertEquals(eventplaces.size(), 3);

        } finally {
            this.eventPlaceDao.clearTable();
            this.eventDayDao.clearTable();
            this.eventDao.clearTable();
        }
    }

}
