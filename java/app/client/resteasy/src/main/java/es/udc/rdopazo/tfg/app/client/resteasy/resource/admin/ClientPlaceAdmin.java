package es.udc.rdopazo.tfg.app.client.resteasy.resource.admin;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.client.resteasy.BaseClient;
import es.udc.rdopazo.tfg.service.api.place.PlaceAdminResource;

@Service
public class ClientPlaceAdmin extends BaseClient<PlaceAdminResource> {

    @Override
    protected Class<PlaceAdminResource> getServiceClass() {
        return PlaceAdminResource.class;
    }

}
