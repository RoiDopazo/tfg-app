package es.udc.rdopazo.tfg.app.client.resteasy.resource;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.client.resteasy.BaseClient;
import es.udc.rdopazo.tfg.service.api.usuario.UsuarioResource;

@Service
public class ClientUser extends BaseClient<UsuarioResource> {

    @Override
    protected Class<UsuarioResource> getServiceClass() {
        return UsuarioResource.class;
    }

}
