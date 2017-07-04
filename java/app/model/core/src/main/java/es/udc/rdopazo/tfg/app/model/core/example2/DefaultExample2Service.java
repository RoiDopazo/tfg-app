package es.udc.rdopazo.tfg.app.model.core.example2;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.etravel.api.example2.Example2Service;

@Service
public class DefaultExample2Service implements Example2Service {

    private static final long serialVersionUID = 1L;

    public String sayHello() {
        return "Hola";
    }

}
