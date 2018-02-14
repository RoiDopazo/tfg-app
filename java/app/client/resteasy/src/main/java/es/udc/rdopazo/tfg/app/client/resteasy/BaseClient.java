package es.udc.rdopazo.tfg.app.client.resteasy;

import java.io.Serializable;

import javax.annotation.PostConstruct;

public abstract class BaseClient<S extends Serializable> {

    private static final String BASE_URI = "http://localhost:8080/rest/";

    protected S service;

    protected abstract Class<S> getServiceClass();

    @PostConstruct
    public void obtainService() {

        // String username = this.propertyManager.getProperty(USERNAME_KEY);
        // String password = this.propertyManager.getEncryptedProperty(PASSWORD_KEY);
        // String url = this.propertyManager.getProperty(URL_KEY);
        //
        // if ((username != null) && (password != null)) {
        // this.service = ProxyFactory.create(this.getServiceClass(), url, this.createExecutor(username, password));
        // } else {
        // LOGGER.error("Can not get user/password");
        // }

        String username = "sa";
        String password = "sa";
        String url = "http://localhost:8080//rest/";

        if ((username != null) && (password != null)) {
            // this.service = ProxyFactory.create(this.getServiceClass(), url);
        } else {
        }
    }
}
