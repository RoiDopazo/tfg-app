package es.udc.rdopazo.tfg.etravel.engine.core.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.etravel.api.example.ExampleDto;
import es.udc.rdopazo.tfg.etravel.api.example.ExampleService;
import es.udc.rdopazo.tfg.etravel.engine.model.jpa.category.JpaCategory;
import es.udc.rdopazo.tfg.etravel.engine.model.jpa.category.dao.JpaCategoryDao;

@Service
public class DefaultExampleService implements ExampleService {

    private static final long serialVersionUID = 5519106903049002242L;

    @Autowired
    JpaCategoryDao catDao;

    public List<ExampleDto> sayHelloDto() {
        List<ExampleDto> list = new ArrayList<ExampleDto>();

        List<JpaCategory> x = this.catDao.getAll();

        for (JpaCategory cat : x) {
            ExampleDto dto = new ExampleDto();
            dto.setNombre(cat.getName());
            dto.setEdad(cat.getId().intValue());
            list.add(dto);
        }

        return list;
    }

}
