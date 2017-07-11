package es.udc.rdopazo.tfg.app.model.core.ruta;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.api.lugar.dto.LugarDto;
import es.udc.rdopazo.tfg.app.api.ruta.RutaService;
import es.udc.rdopazo.tfg.app.api.ruta.dto.RutaDto;
import es.udc.rdopazo.tfg.app.api.rutalugar.dto.RutaLugarDto;
import es.udc.rdopazo.tfg.app.model.core.lugar.converter.LugarEntityDtoConverter;
import es.udc.rdopazo.tfg.app.model.core.ruta.converter.RutaEntityDtoConverter;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.dao.LugarDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.dao.RutaDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.RutaLugar;

@Service
public class DefaultRutaService<R extends Ruta<RL>, L extends Lugar, RL extends RutaLugar<L>> implements RutaService {

    @Autowired
    RutaDao<R> rutaDao;

    @Autowired
    LugarDao<L> lugarDao;

    @Autowired
    RutaEntityDtoConverter<R, RL> rutaConverter;

    @Autowired
    LugarEntityDtoConverter<L> lugarConverter;

    public List<RutaDto> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public RutaDto getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    public RutaDto create() {
        LugarDto l1 = new LugarDto();
        LugarDto l2 = new LugarDto();
        l1.setId_foursquare("1234");
        l2.setId_foursquare("4321");

        RutaLugarDto rl = new RutaLugarDto();
        rl.setLugar(l1);
        RutaLugarDto rl2 = new RutaLugarDto();
        rl2.setLugar(l2);
        List<RutaLugarDto> list = new ArrayList<RutaLugarDto>();
        list.add(rl);
        list.add(rl2);

        RutaDto r = new RutaDto();
        r.setEstado("PENDIENTE");
        r.setRutatLugares(list);

        this.lugarDao.add(this.lugarConverter.toLugarEntity(l1));
        this.lugarDao.add(this.lugarConverter.toLugarEntity(l2));
        try {
            this.rutaDao.add(this.rutaConverter.toRutaEntity(r));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;
    }

}
