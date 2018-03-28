package es.udc.rdopazo.tfg.app.model.core.stay;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public interface StayService<S extends Stay<RD, P, EP>, RD extends RouteDay<?>, P extends Place, EP extends EventPlace<?>> {

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.day.route.user.username == authentication.principal.username or filterObject.day.route.priv == false")
    List<S> getAll(Integer index, Integer count);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.day.route.user.username == authentication.principal.username or filterObject.day.route.priv == false")
    List<S> getByField(String field, String value, Integer index, Integer count);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.day.route.user.username == authentication.principal.username or filterObject.day.route.priv == false")
    List<S> getByFields(Long idRoute, Long idDay, String filter, String value, Integer index, Integer count);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.day.route.user.username == authentication.principal.username or filterObject.day.route.priv == false")
    List<S> getByRouteAndDayAndPlace(Long idRoute, Long idDay, Long idPlace);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.day.route.user.username == authentication.principal.username or filterObject.day.route.priv == false")
    List<S> getAllInDay(Long idRoute, Long idDay);

    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.day.route.user.username == authentication.principal.username or filterObject.day.route.priv == false")
    S getById(Long id) throws InstanceNotFoundException;

    S add(S stay);

    S update(S stay);

    void delete(Long id) throws InstanceNotFoundException;

    Integer getMaxOrderNum(Long idRoute, Long idDay);

    void fixOrdersAfterDelete(Long idRoute, Long idDay);
}
