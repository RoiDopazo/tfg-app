package es.udc.rdopazo.tfg.service.api.stay;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.stay.dto.StayConfListDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayEventPlaceDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayPlaceDto;
import es.udc.rdopazo.tfg.service.api.util.Role;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("stay")
public interface StayResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public List<StayDto> getAll(@QueryParam("idRoute") String idRoute, @QueryParam("idDay") String idDay,
            @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

    @GET
    @Path("{idStay}")
    @Secured({ Role.USER })
    @Produces(MediaType.APPLICATION_JSON)
    public StayDto getById(@PathParam("idStay") String idStay);

    @POST
    @Path("place")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public StayDto createByPlace(@QueryParam("idRoute") String idRoute, @QueryParam("idDay") String idDay,
            StayPlaceDto stayPlaceDto);

    @POST
    @Path("event")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public StayDto createByEventPlace(@QueryParam("idRoute") String idRoute, @QueryParam("idDay") String idDay,
            StayEventPlaceDto stayEventPlaceDto);

    @DELETE
    @Path("{idStay}")
    @Secured({ Role.USER })
    public void delete(@PathParam("idStay") String idStay);

    @PUT
    @Path("/batch")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public List<StayDto> updateBatch(List<StayDto> stayListDto);

    @POST
    @Path("/place/batch")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public Boolean createAndDeleteBatch(@QueryParam("idRoute") String idRoute, StayConfListDto stayConfDto);
}
