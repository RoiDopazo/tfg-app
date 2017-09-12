package es.udc.rdopazo.tfg.service.api.ruta;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.ruta.dto.RutaLugarDto;

@Path("route/{id_ruta}/place")
public interface RutaLugarResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RutaLugarDto> getAll(@PathParam("id_ruta") String id_ruta);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RutaLugarDto create(@PathParam("id_ruta") String id_ruta, RutaLugarDto rutaLugarDto);

}
