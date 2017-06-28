package es.udc.rdopazo.tfg.etravel.application.resteasy.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import es.udc.rdopazo.tfg.etravel.api.example.ExampleService;
import es.udc.rdopazo.tfg.etravel.api.example2.Example2Service;
import es.udc.rdopazo.tfg.etravel.application.resteasy.spring.SpringApplicationContext;

public class TravelRestApplication extends Application {

    private Set<Object> singletons;

    public TravelRestApplication() {
        this.singletons = new HashSet<Object>();
        // this.singletons.add(this.getBean(Filter.class));
        this.singletons.add(this.getBean(ExampleService.class));
        this.singletons.add(this.getBean(Example2Service.class));
    }

    @Override
    public Set<Object> getSingletons() {
        return this.singletons;
    }

    protected Object getBean(Class<?> clazz) {
        return SpringApplicationContext.getBean(clazz);
    }
}
