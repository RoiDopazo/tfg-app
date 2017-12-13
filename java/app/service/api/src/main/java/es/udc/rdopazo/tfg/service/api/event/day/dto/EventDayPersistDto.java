package es.udc.rdopazo.tfg.service.api.event.day.dto;

import java.util.Date;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class EventDayPersistDto implements EntityDto {

    private static final long serialVersionUID = -5884280373292826547L;

    private Date date;

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
