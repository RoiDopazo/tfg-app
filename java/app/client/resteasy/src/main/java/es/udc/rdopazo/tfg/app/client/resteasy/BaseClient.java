package es.udc.rdopazo.tfg.app.client.resteasy;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public abstract class BaseClient<S extends Serializable> {

    private static final String BASE_URI = "http://localhost:8080/rest/";

    protected S service;

    protected abstract Class<S> getServiceClass();

    @PostConstruct
    public void obtainService() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(BASE_URI);
        client.register(new HeaderFilter());
        this.service = target.proxy(this.getServiceClass());
    }
}
