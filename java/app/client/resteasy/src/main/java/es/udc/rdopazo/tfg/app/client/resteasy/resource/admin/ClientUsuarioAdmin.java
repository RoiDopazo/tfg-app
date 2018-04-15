package es.udc.rdopazo.tfg.app.client.resteasy.resource.admin;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.client.resteasy.BaseClient;
import es.udc.rdopazo.tfg.service.api.usuario.UsuarioAdminResource;

@Service
public class ClientUsuarioAdmin extends BaseClient<UsuarioAdminResource> {

    @Override
    protected Class<UsuarioAdminResource> getServiceClass() {
        return UsuarioAdminResource.class;
    }

}
