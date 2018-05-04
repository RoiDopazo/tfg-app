package es.udc.rdopazo.tfg.app.model.core.event;

import java.util.Date;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public interface EventService<E extends Event<ED>, ED extends EventDay<E, EP>, EP extends EventPlace<ED>> {

    List<E> getAllEvents(Integer index, Integer count);

    List<E> getEventsByField(String field, Object value, Integer index, Integer count);

    E getEventById(Long id) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    E addEvent(E event);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    E updateEvent(E event);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    void deleteEvent(Long id) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    ED addEventDay(Long idEvent, ED day) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    ED updateEventDay(ED day);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    void deleteEventDay(Long idEvent, Long idDay) throws InstanceNotFoundException;

    List<ED> getAllEventDays(Long idEvent, Integer index, Integer count);

    List<ED> getAllEventDays(Integer index, Integer count);

    List<ED> getEventDaysByField(String field, Object value, Integer index, Integer count);

    List<ED> getEventDaysByFields(Long idEvent, Long idDay, String filter, Object value, Integer index, Integer count)
            throws InstanceNotFoundException;

    ED getEventDayById(Long idEvent, Long idDay) throws InstanceNotFoundException;

    List<ED> getAllEventDaysByDateRange(String city, Date start_date, Date end_date, String type, Integer index,
            Integer count);

    List<EP> getAllEventPlaces(Integer index, Integer count);

    EP getEventPlaceById(Long id) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    EP addEventPlace(Long idEvent, Long idDay, EP eventPlace) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    EP updateEventPlace(EP eventPlace);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    void deleteEventPlace(Long id) throws InstanceNotFoundException;

    List<EP> getEventPlacesByFields(Long idEvent, Long idDay, String filter, Object value, Integer index,
            Integer count);

}
