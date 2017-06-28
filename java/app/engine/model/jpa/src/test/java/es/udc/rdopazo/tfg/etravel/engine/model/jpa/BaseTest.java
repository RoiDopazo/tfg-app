package es.udc.rdopazo.tfg.etravel.engine.model.jpa;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:es/udc/rdopazo/tfg/etravel/engine/model/jpa/spring/persistence-config-test.xml" })
public abstract class BaseTest {

}