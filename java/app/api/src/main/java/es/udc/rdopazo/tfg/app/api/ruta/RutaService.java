package es.udc.rdopazo.tfg.app.api.ruta;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.app.api.ruta.dto.RutaDto;

@Path("ruta")
public interface RutaService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RutaDto> getAll();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RutaDto getById(@PathParam("id") String id);

    @GET
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public RutaDto create();
}
