package es.udc.rdopazo.tfg.etravel.application.resteasy.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import es.udc.rdopazo.tfg.app.api.categoria.CategoriaService;
import es.udc.rdopazo.tfg.app.api.comentario.ComentarioService;
import es.udc.rdopazo.tfg.app.api.lugar.LugarService;
import es.udc.rdopazo.tfg.app.api.ruta.RutaLugarService;
import es.udc.rdopazo.tfg.app.api.ruta.RutaService;
import es.udc.rdopazo.tfg.app.api.subcategoria.SubCategoriaService;
import es.udc.rdopazo.tfg.app.api.usuario.UsuarioService;
import es.udc.rdopazo.tfg.etravel.application.resteasy.spring.SpringApplicationContext;

public class TravelRestApplication extends Application {

    private Set<Object> singletons;

    public TravelRestApplication() {
        this.singletons = new HashSet<Object>();
        this.singletons.add(this.getBean(CategoriaService.class));
        this.singletons.add(this.getBean(SubCategoriaService.class));
        this.singletons.add(this.getBean(RutaService.class));
        this.singletons.add(this.getBean(LugarService.class));
        this.singletons.add(this.getBean(UsuarioService.class));
        this.singletons.add(this.getBean(ComentarioService.class));
        this.singletons.add(this.getBean(RutaLugarService.class));

    }

    @Override
    public Set<Object> getSingletons() {
        return this.singletons;
    }

    protected Object getBean(Class<?> clazz) {
        return SpringApplicationContext.getBean(clazz);
    }
}
