package es.udc.rdopazo.tfg.app.model.persistence.util;

import java.io.Serializable;

public interface Entity<E extends Serializable> extends Serializable {

    E getId();

    void setId(E id);
}
