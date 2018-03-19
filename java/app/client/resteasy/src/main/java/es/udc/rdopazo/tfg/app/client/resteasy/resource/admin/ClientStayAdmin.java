package es.udc.rdopazo.tfg.app.client.resteasy.resource.admin;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.client.resteasy.BaseClient;
import es.udc.rdopazo.tfg.service.api.stay.StayAdminResource;

@Service
public class ClientStayAdmin extends BaseClient<StayAdminResource> {

    @Override
    protected Class<StayAdminResource> getServiceClass() {
        return StayAdminResource.class;
    }

}
