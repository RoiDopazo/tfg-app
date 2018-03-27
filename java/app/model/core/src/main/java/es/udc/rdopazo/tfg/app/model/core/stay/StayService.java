package es.udc.rdopazo.tfg.app.model.core.stay;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public interface StayService<S extends Stay<?, ?, ?>> {

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

    @PreAuthorize("hasRole('ROLE_ADMIN') or @mySecurityService.hasStayPermission2(authentication, #stay)")
    S add(S stay);

    @PreAuthorize("hasRole('ROLE_ADMIN') or @mySecurityService.hasStayPermission(authentication, #stay.id)")
    S update(S stay);

    @PreAuthorize("hasRole('ROLE_ADMIN') or @mySecurityService.hasStayPermission(authentication, #id)")
    void delete(Long id) throws InstanceNotFoundException;

    Integer getMaxOrderNum(Long idRoute, Long idDay);

    void fixOrdersAfterDelete(Long idRoute, Long idDay);
}
