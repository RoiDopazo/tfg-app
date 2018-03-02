package es.udc.rdopazo.tfg.service.api.realtimedata;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.realtimedata.dto.RealTimeDataDto;

@Path("realtimedata")
public interface RealTimeDataResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RealTimeDataDto> getAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RealTimeDataDto create(RealTimeDataDto realTimeDataDto);

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RealTimeDataDto add(RealTimeDataDto realTimeDataDto);
}
