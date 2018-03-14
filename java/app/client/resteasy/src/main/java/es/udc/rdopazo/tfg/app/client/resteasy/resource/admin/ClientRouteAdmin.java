package es.udc.rdopazo.tfg.app.client.resteasy.resource.admin;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.client.resteasy.BaseClient;
import es.udc.rdopazo.tfg.service.api.route.RouteAdminResource;

@Service
public class ClientRouteAdmin extends BaseClient<RouteAdminResource> {

    @Override
    protected Class<RouteAdminResource> getServiceClass() {
        return RouteAdminResource.class;
    }

}
