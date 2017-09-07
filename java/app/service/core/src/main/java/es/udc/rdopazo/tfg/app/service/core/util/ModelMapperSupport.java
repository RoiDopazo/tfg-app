package es.udc.rdopazo.tfg.app.service.core.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperSupport {

    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
