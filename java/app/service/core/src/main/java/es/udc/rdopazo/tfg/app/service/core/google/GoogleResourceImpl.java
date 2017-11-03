package es.udc.rdopazo.tfg.app.service.core.google;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.google.GoogleService;
import es.udc.rdopazo.tfg.service.api.google.GoogleResource;

@Service
public class GoogleResourceImpl implements GoogleResource {

    @Autowired
    private GoogleService googleService;

    public void getTravelInfo() {
        this.googleService.getTravelInfo(40.416891223065306, -3.7022561514044563, 40.41649931761419,
                -3.7028530984138963);
    }

}
