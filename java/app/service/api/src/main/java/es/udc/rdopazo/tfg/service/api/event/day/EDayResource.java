package es.udc.rdopazo.tfg.service.api.event.day;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.event.day.dto.DateRangeDto;
import es.udc.rdopazo.tfg.service.api.event.day.dto.EventDayDto;

@Path("eventday")
public interface EDayResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<EventDayDto> getAll(@DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

    @GET
    @Path("/date_over")
    @Produces(MediaType.APPLICATION_JSON)
    List<EventDayDto> getAllByDateOver(@DefaultValue("null") @QueryParam("value") String dateValue,
            @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

    @GET
    @Path("/date_between")
    @Produces(MediaType.APPLICATION_JSON)
    List<EventDayDto> getAllByDateBetween(@DefaultValue("null") @QueryParam("value1") String value1,
            @DefaultValue("null") @QueryParam("value2") String value2,
            @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<EventDayDto> getAllByDateRange(@DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count, DateRangeDto dateDto);

}