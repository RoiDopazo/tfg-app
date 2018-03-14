package es.udc.rdopazo.tfg.app.client.resteasy.resource;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.client.resteasy.BaseClient;
import es.udc.rdopazo.tfg.service.api.route.RouteResource;

@Service
public class ClientRoute extends BaseClient<RouteResource> {

    private static final long serialVersionUID = 7251667393301687873L;

    @Override
    protected Class<RouteResource> getServiceClass() {
        return RouteResource.class;
    }

}
