package es.udc.rdopazo.tfg.service.api.route.day;

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

import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.Role;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RealTimeDataDto;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RouteDayDto;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("route/{idRoute}/day")
@Secured
public interface RouteDayResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    List<RouteDayDto> getAll(@PathParam("idRoute") String idRoute,
            @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

    @PUT
    @Path("{idDay}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public RouteDayDto update(@PathParam("idRoute") String idRoute, @PathParam("idDay") String idDay,
            RouteDayDto diaDto) throws InstanceNotFoundException;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    RouteDayDto create(@PathParam("idRoute") String idRoute) throws InstanceNotFoundException;

    @POST
    @Path("/setNumDays")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    List<RouteDayDto> createNumDays(@PathParam("idRoute") String idRoute, Integer numDays)
            throws InstanceNotFoundException;

    @DELETE
    @Secured({ Role.USER })
    void delete(@PathParam("idRoute") String idRoute) throws InstanceNotFoundException;

    @POST
    @Path("/calculateHours")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    RouteDayDto calculateHours(@PathParam("idRoute") String idRoute, RouteDayDto diaDto);

    @POST
    @Path("{idDay}/realtimedata")
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    void addRealTimeData(@PathParam("idRoute") String idRoute, @PathParam("idDay") String idDay,
            RealTimeDataDto realTimeDataDto) throws InstanceNotFoundException;
}
