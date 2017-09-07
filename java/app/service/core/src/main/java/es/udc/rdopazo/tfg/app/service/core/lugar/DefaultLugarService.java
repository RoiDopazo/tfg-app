package es.udc.rdopazo.tfg.app.service.core.lugar;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.dao.LugarDao;
import es.udc.rdopazo.tfg.app.service.core.lugar.converter.LugarEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.lugar.updater.LugarEntityDtoUpdater;
import es.udc.rdopazo.tfg.service.api.lugar.LugarService;
import es.udc.rdopazo.tfg.service.api.lugar.dto.LugarDto;

@Service
public class DefaultLugarService<L extends Lugar> implements LugarService {

    @Autowired
    LugarDao<L> dao;

    @Autowired
    LugarEntityDtoConverter<LugarDto, L> converter;

    @Autowired
    LugarEntityDtoUpdater<L> updater;

    public List<LugarDto> getAll() {

        return this.converter.toDtoList(this.dao.getAll());
    }

    public LugarDto getById(String id) {
        L lugar = null;
        try {
            lugar = this.dao.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(lugar);
    }

    @Transactional
    public LugarDto create(LugarDto lugarDto) {
        L lugar = this.converter.toEntity(lugarDto);
        this.dao.add(lugar);
        return this.converter.toDto(lugar);
    }

    @Transactional
    public LugarDto update(String id, LugarDto lugarDto) {
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
