package es.udc.rdopazo.tfg.app.model.core.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.persistence.jpa.categoria.JpaCategoria;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.categoria.dao.JpaCategoriaDao;
import es.udc.rdopazo.tfg.etravel.api.example.ExampleDto;
import es.udc.rdopazo.tfg.etravel.api.example.ExampleService;

@Service
public class DefaultExampleService implements ExampleService {

    private static final long serialVersionUID = 5519106903049002242L;

    @Autowired
    JpaCategoriaDao catDao;

    public List<ExampleDto> sayHelloDto() {
        List<ExampleDto> list = new ArrayList<ExampleDto>();

        List<JpaCategoria> x = this.catDao.getAll();

        for (JpaCategoria cat : x) {
            ExampleDto dto = new ExampleDto();
            dto.setNombre(cat.getNombre());
            dto.setEdad(cat.getId().intValue());
            list.add(dto);
        }

        return list;
    }

}
