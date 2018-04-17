package es.udc.rdopazo.tfg.app.client.resteasy.resource.admin;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.client.resteasy.BaseClient;
import es.udc.rdopazo.tfg.service.api.subcategory.SubCategoryAdminResource;

@Service
public class ClientSubCategoryAdmin extends BaseClient<SubCategoryAdminResource> {

    @Override
    protected Class<SubCategoryAdminResource> getServiceClass() {
        return SubCategoryAdminResource.class;
    }

}