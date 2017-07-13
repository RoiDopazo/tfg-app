package es.udc.rdopazo.tfg.app.model.core.lugar;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.api.lugar.LugarService;
import es.udc.rdopazo.tfg.app.api.lugar.dto.LugarDto;
import es.udc.rdopazo.tfg.app.model.core.lugar.converter.LugarEntityDtoConverter;
import es.udc.rdopazo.tfg.app.model.core.lugar.updater.LugarEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.dao.LugarDao;

@Service
public class DefaultLugarService<D extends LugarDto, L extends Lugar> implements LugarService<D> {

    @Autowired
    LugarDao<L> dao;

    @Autowired
    LugarEntityDtoConverter<D, L> converter;

    @Autowired
    LugarEntityDtoUpdater<L> updater;

    public List<D> getAll() {

        return this.converter.toDtoList(this.dao.getAll());
    }

    public D getById(String id) {
        L lugar = null;
        try {
            lugar = this.dao.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(lugar);
    }

    @Transactional
    public D create(LugarDto lugarDto) {

        @SuppressWarnings("unchecked")
        L lugar = this.converter.toEntity((D) lugarDto);
        this.dao.add(lugar);
        return this.converter.toDto(lugar);
    }

    @Transactional
    public D update(String id, LugarDto lugarDto) {
        L lugar = null;
        try {
            lugar = this.dao.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        lugar = this.updater.update(lugarDto, lugar);
        this.dao.update(lugar);
        return this.converter.toDto(lugar);
    }

    @Transactional
    public void delete(String id) {
        try {
            this.dao.remove(this.dao.getById(Long.parseLong(id)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
