package es.udc.rdopazo.tfg.app.model.core.lugar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.api.lugar.LugarService;
import es.udc.rdopazo.tfg.app.api.lugar.dto.LugarDto;
import es.udc.rdopazo.tfg.app.model.core.util.ModelMapperSupport;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.dao.LugarDao;

@Service
public class DefaultLugarService<L extends Lugar> implements LugarService {

    @Autowired
    LugarDao<L> lugarDao;

    @Autowired
    ModelMapperSupport modelMapper;

    public List<LugarDto> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public LugarDto getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

}
