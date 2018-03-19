package es.udc.rdopazo.tfg.app.client.resteasy.resource.admin;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.client.resteasy.BaseClient;
import es.udc.rdopazo.tfg.service.api.event.EventAdminResource;

@Service
public class ClientEventAdmin extends BaseClient<EventAdminResource> {

    @Override
    protected Class<EventAdminResource> getServiceClass() {
        return EventAdminResource.class;
    }

}
