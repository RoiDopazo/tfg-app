package es.udc.rdopazo.tfg.app.client.resteasy;

import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.service.api.route.RouteResource;
import es.udc.rdopazo.tfg.service.api.route.dto.RouteDto;

@Service
public class ClientRouteResource extends BaseClient<RouteResource> implements RouteResource {

    private static final long serialVersionUID = 7251667393301687873L;

    @Override
    protected Class<RouteResource> getServiceClass() {
        return RouteResource.class;
    }

    public List<RouteDto> getAll(String index, String count) {

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/rest/");
        RouteResource routeResource = target.proxy(RouteResource.class);
        return routeResource.getAll(index, count);
    }

    public RouteDto getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public RouteDto create(RouteDto rutaDto) {
        // TODO Auto-generated method stub
        return null;
    }

    public RouteDto update(String id, RouteDto rutaDto) {
        // TODO Auto-generated method stub
        return null;
    }

    public void delete(String id) {
        // TODO Auto-generated method stub

    }

}
