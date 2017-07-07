package es.udc.rdopazo.tfg.etravel.application.resteasy.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import es.udc.rdopazo.tfg.app.api.categoria.CategoriaService;
import es.udc.rdopazo.tfg.app.api.subcategoria.SubCategoriaService;
import es.udc.rdopazo.tfg.etravel.application.resteasy.spring.SpringApplicationContext;

public class TravelRestApplication extends Application {

    private Set<Object> singletons;

    public TravelRestApplication() {
        this.singletons = new HashSet<Object>();
        this.singletons.add(this.getBean(CategoriaService.class));
        this.singletons.add(this.getBean(SubCategoriaService.class));
    }

    @Override
    public Set<Object> getSingletons() {
        return this.singletons;
    }

    protected Object getBean(Class<?> clazz) {
        return SpringApplicationContext.getBean(clazz);
    }
}
