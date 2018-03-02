package es.udc.rdopazo.tfg.app.application.resteasy.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import es.udc.rdopazo.tfg.app.application.resteasy.spring.SpringApplicationContext;
import es.udc.rdopazo.tfg.app.model.core.usuario.UsuarioService;
import es.udc.rdopazo.tfg.app.service.core.util.TokenServices;
import es.udc.rdopazo.tfg.service.api.category.CategoryResource;
import es.udc.rdopazo.tfg.service.api.comentario.ComentarioResource;
import es.udc.rdopazo.tfg.service.api.event.EventResource;
import es.udc.rdopazo.tfg.service.api.event.day.EDayResource;
import es.udc.rdopazo.tfg.service.api.event.day.EventDayResource;
import es.udc.rdopazo.tfg.service.api.event.place.EventPlaceResource;
import es.udc.rdopazo.tfg.service.api.foursquare.FoursquareResource;
import es.udc.rdopazo.tfg.service.api.place.PlaceResource;
import es.udc.rdopazo.tfg.service.api.realtimedata.RealTimeDataResource;
import es.udc.rdopazo.tfg.service.api.route.RouteResource;
import es.udc.rdopazo.tfg.service.api.route.day.RouteDayResource;
import es.udc.rdopazo.tfg.service.api.stay.StayResource;
import es.udc.rdopazo.tfg.service.api.subcategory.SubCategoryResource;
import es.udc.rdopazo.tfg.service.api.usuario.UsuarioResource;

public class TravelRestApplication extends Application {

    private Set<Object> singletons;

    public TravelRestApplication() {
        this.singletons = new HashSet<Object>();
        this.singletons.add(this.getBean(CategoryResource.class));
        this.singletons.add(this.getBean(SubCategoryResource.class));
        this.singletons.add(this.getBean(RouteResource.class));
        this.singletons.add(this.getBean(PlaceResource.class));
        this.singletons.add(this.getBean(UsuarioResource.class));
        this.singletons.add(this.getBean(ComentarioResource.class));
        this.singletons.add(this.getBean(RouteDayResource.class));
        this.singletons.add(this.getBean(StayResource.class));
        this.singletons.add(this.getBean(EventResource.class));
        this.singletons.add(this.getBean(EventDayResource.class));
        this.singletons.add(this.getBean(EDayResource.class));
        this.singletons.add(this.getBean(EventPlaceResource.class));
        this.singletons.add(this.getBean(FoursquareResource.class));
        this.singletons.add(this.getBean(RealTimeDataResource.class));

        this.singletons.add(this.getBean(UsuarioService.class));
        this.singletons.add(this.getBean(TokenServices.class));

    }

    @Override
    public Set<Object> getSingletons() {
        return this.singletons;
    }

    protected Object getBean(Class<?> clazz) {
        return SpringApplicationContext.getBean(clazz);
    }
}
