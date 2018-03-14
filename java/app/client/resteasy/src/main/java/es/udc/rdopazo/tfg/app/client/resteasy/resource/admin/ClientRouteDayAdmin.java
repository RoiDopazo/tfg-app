package es.udc.rdopazo.tfg.app.client.resteasy.resource.admin;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.client.resteasy.BaseClient;
import es.udc.rdopazo.tfg.service.api.route.day.RouteDayAdminResource;

@Service
public class ClientRouteDayAdmin extends BaseClient<RouteDayAdminResource> {

    @Override
    protected Class<RouteDayAdminResource> getServiceClass() {
        return RouteDayAdminResource.class;
    }

}
