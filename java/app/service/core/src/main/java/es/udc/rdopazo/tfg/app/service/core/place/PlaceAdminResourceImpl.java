package es.udc.rdopazo.tfg.app.service.core.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.place.PlaceService;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.service.core.place.converter.PlacePersistEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.place.updater.PlaceEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.place.PlaceAdminResource;
import es.udc.rdopazo.tfg.service.api.place.dto.PlacePersistDto;

@Service
public class PlaceAdminResourceImpl<P extends Place> implements PlaceAdminResource {

    @Autowired
    private PlaceService<P> service;

    @Autowired
    private PlacePersistEntityDtoConverter<PlacePersistDto, P> converter;

    @Autowired
    private PlaceEntityDtoUpdater<P> updater;

    public List<PlacePersistDto> getAll(String filter, String value, String index, String count)
            throws InputValidationException {

        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);

        List<PlacePersistDto> result = this.converter
                .toDtoList(this.service.getListByField(filter, value, indexInt, countInt));
        return result;
    }

    public PlacePersistDto getById(String id) throws InstanceNotFoundException, InputValidationException {
        Long idPlace = InputValidator.validateLongNull("idPlace", id);
        P place = this.service.getById(idPlace);
        return this.converter.toDto(place);
    }

    public PlacePersistDto update(String id, PlacePersistDto placePersistDto)
            throws InstanceNotFoundException, InputValidationException {

        Long idPlace = InputValidator.validateLongNull("idPlace", id);
        P place = this.service.getById(idPlace);
        place = this.updater.updatePersist(placePersistDto, place);
        return this.converter.toDto(this.service.update(place));
    }

    public void delete(String id) throws InputValidationException, InstanceNotFoundException {
        Long idPlace = InputValidator.validateLongNull("idPlace", id);
        this.service.delete(idPlace);

    }

}
