package es.udc.rdopazo.tfg.app.client.resteasy.resource.admin;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.client.resteasy.BaseClient;
import es.udc.rdopazo.tfg.service.api.event.day.EventDayAdminResource;

@Service
public class ClientEventDayAdmin extends BaseClient<EventDayAdminResource> {

    @Override
    protected Class<EventDayAdminResource> getServiceClass() {
        return EventDayAdminResource.class;
    }

}
