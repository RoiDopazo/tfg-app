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

import es.udc.rdopazo.tfg.app.util.enums.Role;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.UnUpdateableRouteException;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayConfListDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayEventPlaceDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayPlaceDto;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("stay")
@Secured({ Role.USER })
public interface StayResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<StayDto> getAll(@DefaultValue("") @QueryParam("idRoute") String idRoute,
            @QueryParam("idDay") String idDay, @DefaultValue("") @QueryParam("index") String index,
            @DefaultValue("") @QueryParam("count") String count);

    @GET
    @Path("{idStay}")
    @Produces(MediaType.APPLICATION_JSON)
    public StayDto getById(@PathParam("idStay") String idStay) throws InstanceNotFoundException;

    @POST
    @Path("place")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StayDto createByPlace(@DefaultValue("") @QueryParam("idRoute") String idRoute,
            @DefaultValue("") @QueryParam("idDay") String idDay, StayPlaceDto stayPlaceDto)
            throws InstanceNotFoundException, UnUpdateableRouteException;

    @POST
    @Path("event")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StayDto createByEventPlace(@QueryParam("idRoute") String idRoute, @QueryParam("idDay") String idDay,
            StayEventPlaceDto stayEventPlaceDto) throws InstanceNotFoundException, UnUpdateableRouteException;

    @DELETE
    @Path("{idStay}")
    public void delete(@PathParam("idStay") String idStay) throws InstanceNotFoundException, UnUpdateableRouteException;

    @PUT
    @Path("/batch")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<StayDto> updateBatch(List<StayDto> stayListDto)
            throws InstanceNotFoundException, UnUpdateableRouteException;

    @POST
    @Path("/place/batch")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean createAndDeleteBatch(@DefaultValue("") @QueryParam("idRoute") String idRoute,
            StayConfListDto stayConfDto) throws InstanceNotFoundException, UnUpdateableRouteException;
}
