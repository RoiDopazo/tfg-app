package es.udc.rdopazo.tfg.app.client.resteasy.resource.admin;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.client.resteasy.BaseClient;
import es.udc.rdopazo.tfg.service.api.event.place.EventPlaceAdminResource;

@Service
public class ClientEventPlaceAdmin extends BaseClient<EventPlaceAdminResource> {

    @Override
    protected Class<EventPlaceAdminResource> getServiceClass() {
        return EventPlaceAdminResource.class;
    }

}
