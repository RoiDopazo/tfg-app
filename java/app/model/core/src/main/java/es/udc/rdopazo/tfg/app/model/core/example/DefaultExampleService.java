package es.udc.rdopazo.tfg.app.model.core.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;
import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.dao.CategoriaDao;
import es.udc.rdopazo.tfg.etravel.api.example.ExampleDto;
import es.udc.rdopazo.tfg.etravel.api.example.ExampleService;

@Service
public class DefaultExampleService<C extends Categoria> implements ExampleService {

    private static final long serialVersionUID = 5519106903049002242L;

    @Autowired
    CategoriaDao<C> catDao;

    public List<ExampleDto> sayHelloDto() {
        List<ExampleDto> list = new ArrayList<ExampleDto>();

        List<C> x = this.catDao.getAll();

        for (C cat : x) {
            ExampleDto dto = new ExampleDto();
            dto.setNombre(cat.getNombre());
            dto.setEdad(cat.getId().intValue());
            list.add(dto);
        }

        return list;
    }

}
