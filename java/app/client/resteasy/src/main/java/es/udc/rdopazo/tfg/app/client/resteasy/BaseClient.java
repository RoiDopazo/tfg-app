package es.udc.rdopazo.tfg.app.client.resteasy;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import es.udc.rdopazo.tfg.app.client.resteasy.filter.HeaderFilter;
import es.udc.rdopazo.tfg.app.util.config.ConfigurationParametersManager;

public abstract class BaseClient<S extends Serializable> {

    private final static String BASE_URI = ConfigurationParametersManager.getParameter("BaseUri.rest-service");

    protected S service;

    private ResteasyClient client;

    protected abstract Class<S> getServiceClass();

    @PostConstruct
    public void obtainService() {
        this.client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = this.client.target(BASE_URI);
        HeaderFilter hf = new HeaderFilter();
        this.client.register(hf);
        this.service = target.proxy(this.getServiceClass());
    }

    public S getService(String token) {

        Set<Object> registered = this.client.getConfiguration().getInstances();
        Iterator<Object> iterator = registered.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object instanceof HeaderFilter) {
                HeaderFilter headerFilter = (HeaderFilter) object;
                headerFilter.setToken(token);
            }
        }
        return this.service;
    }
}
