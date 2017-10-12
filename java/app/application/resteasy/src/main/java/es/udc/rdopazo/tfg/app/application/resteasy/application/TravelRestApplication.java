package es.udc.rdopazo.tfg.app.application.resteasy.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import es.udc.rdopazo.tfg.app.application.resteasy.spring.SpringApplicationContext;
import es.udc.rdopazo.tfg.app.model.core.usuario.UsuarioService;
import es.udc.rdopazo.tfg.service.api.categoria.CategoriaResource;
import es.udc.rdopazo.tfg.service.api.comentario.ComentarioResource;
import es.udc.rdopazo.tfg.service.api.dia.DiaResource;
import es.udc.rdopazo.tfg.service.api.foursquare.FoursquareResource;
import es.udc.rdopazo.tfg.service.api.lugar.LugarResource;
import es.udc.rdopazo.tfg.service.api.ruta.RutaResource;
import es.udc.rdopazo.tfg.service.api.subcategoria.SubCategoriaResource;
import es.udc.rdopazo.tfg.service.api.usuario.UsuarioResource;

public class TravelRestApplication extends Application {

    private Set<Object> singletons;

    public TravelRestApplication() {
        this.singletons = new HashSet<Object>();
        this.singletons.add(this.getBean(CategoriaResource.class));
        this.singletons.add(this.getBean(SubCategoriaResource.class));
        this.singletons.add(this.getBean(RutaResource.class));
        this.singletons.add(this.getBean(LugarResource.class));
        this.singletons.add(this.getBean(UsuarioResource.class));
        this.singletons.add(this.getBean(ComentarioResource.class));
        this.singletons.add(this.getBean(DiaResource.class));
        this.singletons.add(this.getBean(FoursquareResource.class));

        this.singletons.add(this.getBean(UsuarioService.class));

    }

    @Override
    public Set<Object> getSingletons() {
        return this.singletons;
    }

    protected Object getBean(Class<?> clazz) {
        return SpringApplicationContext.getBean(clazz);
    }
}
