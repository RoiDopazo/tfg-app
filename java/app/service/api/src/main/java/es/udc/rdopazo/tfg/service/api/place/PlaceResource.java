package es.udc.rdopazo.tfg.service.api.place;

import javax.ws.rs.Path;

import es.udc.rdopazo.tfg.app.util.enums.Role;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("place")
@Secured({ Role.USER })
public interface PlaceResource {

}
