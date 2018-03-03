package es.udc.rdopazo.tfg.app.service.core.route.day.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RealTimeDataDto;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RouteDayDto;

@Component
public class RouteDayEntityDtoUpdater<D extends RouteDay<?>> {

    public D update(RouteDayDto dto, D routeDay) {
        routeDay.setStartTime(dto.getStartTime());
        return routeDay;
    }

    public D updateRTD(RealTimeDataDto dto, D routeDay) {
        String locations = null;
        if (routeDay.getRealTimeData() == null) {
            locations = new StringBuilder().append("[{'lat':").append(dto.getLat().trim()).append(",'lng':")
                    .append(dto.getLng().trim()).append("}]").toString();
        } else {
            String lastLoc = routeDay.getRealTimeData();
            lastLoc = lastLoc.substring(0, lastLoc.length() - 1);
            locations = new StringBuilder().append(lastLoc).append(",{'lat':").append(dto.getLat().trim())
                    .append(",'lng':").append(dto.getLng().trim()).append("}]").toString();
        }

        System.out.println(locations);
        routeDay.setRealTimeData(locations);
        return routeDay;
    }
}