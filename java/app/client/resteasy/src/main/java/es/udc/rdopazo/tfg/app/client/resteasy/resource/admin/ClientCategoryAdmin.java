package es.udc.rdopazo.tfg.app.client.resteasy.resource.admin;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.client.resteasy.BaseClient;
import es.udc.rdopazo.tfg.service.api.category.CategoryAdminResource;

@Service
public class ClientCategoryAdmin extends BaseClient<CategoryAdminResource> {

    @Override
    protected Class<CategoryAdminResource> getServiceClass() {
        return CategoryAdminResource.class;
    }

}
