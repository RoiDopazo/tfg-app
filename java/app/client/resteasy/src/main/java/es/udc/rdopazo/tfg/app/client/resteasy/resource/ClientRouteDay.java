package es.udc.rdopazo.tfg.app.client.resteasy.resource;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.client.resteasy.BaseClient;
import es.udc.rdopazo.tfg.service.api.route.day.RouteDayResource;

@Service
public class ClientRouteDay extends BaseClient<RouteDayResource> {

    @Override
    protected Class<RouteDayResource> getServiceClass() {
        return RouteDayResource.class;
    }

}
