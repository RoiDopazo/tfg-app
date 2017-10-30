package es.udc.rdopazo.tfg.app.application.resteasy.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import es.udc.rdopazo.tfg.app.application.resteasy.util.filter.TokenEncription;

public class MyServlet implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        if (!TokenEncription.areKeysPresent()) {
//            TokenEncription.generateKey();
//        }
    }

}
