package es.udc.rdopazo.tfg.etravel.application.resteasy.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext appContext;

    // Private constructor prevents instantiation from other classes
    private SpringApplicationContext() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;

    }

    public static Object getBean(Class<?> clazz) {
        return appContext.getBean(clazz);
    }

}
