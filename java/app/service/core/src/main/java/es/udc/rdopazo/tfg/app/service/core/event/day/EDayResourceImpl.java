package es.udc.rdopazo.tfg.app.service.core.event.day;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.day.EventDayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.service.core.event.day.converter.EventDayEntityDtoConverter;
import es.udc.rdopazo.tfg.service.api.event.day.EDayResource;
import es.udc.rdopazo.tfg.service.api.event.day.dto.DateRangeDto;
import es.udc.rdopazo.tfg.service.api.event.day.dto.EventDayDto;

@Service
public class EDayResourceImpl<E extends Event<ED>, ED extends EventDay<E, EP>, EP extends EventPlace<ED>>
        implements EDayResource {

    @Autowired
    private EventDayService<ED> service;

    @Autowired
    private EventDayEntityDtoConverter<EventDayDto, E, ED, EP> converter;

    public List<EventDayDto> getAll(String index, String count) {
        Integer indexInt = null;
        Integer countInt = null;

        try {
            indexInt = Integer.parseInt(index);
        } catch (NumberFormatException e) {

        }
        try {
            countInt = Integer.parseInt(count);
        } catch (NumberFormatException e) {

        }
        return this.converter.toDtoList(this.service.getAll(indexInt, countInt));
    }

    public List<EventDayDto> getAllByDateRange(String city, String index, String count, DateRangeDto dateDto) {
        Integer indexInt = null;
        Integer countInt = null;

        try {
            indexInt = Integer.parseInt(index);
        } catch (NumberFormatException e) {

        }
        try {
            countInt = Integer.parseInt(count);
        } catch (NumberFormatException e) {

        }
        Date start_date = dateDto.getStart_date();
        Date end_date = dateDto.getEnd_date();
        String type = dateDto.getType();
        return this.converter
                .toDtoList(this.service.getAllByDateRange(city, start_date, end_date, type, indexInt, countInt));
    }

    public List<EventDayDto> getAllByDateOver(String city, String dateValue, String index, String count) {
        Integer indexInt = null;
        Integer countInt = null;
        Long dateValueMillis = null;

        try {
            dateValueMillis = Long.parseLong(dateValue);
        } catch (NumberFormatException e) {

        }

        try {
            indexInt = Integer.parseInt(index);
        } catch (NumberFormatException e) {

        }
        try {
            countInt = Integer.parseInt(count);
        } catch (NumberFormatException e) {

        }

        Date date = new Date(dateValueMillis);
        return this.converter.toDtoList(this.service.getAllByDateRange(city, null, date, "OVER", indexInt, countInt));
    }

    public List<EventDayDto> getAllByDateBetween(String city, String value1, String value2, String index,
            String count) {
        Integer indexInt = null;
        Integer countInt = null;
        Long dateValue1 = null;
        Long dateValue2 = null;

        try {
            dateValue1 = Long.parseLong(value1);
        } catch (NumberFormatException e) {

        }

        try {
            dateValue2 = Long.parseLong(value2);
        } catch (NumberFormatException e) {

        }

        try {
            indexInt = Integer.parseInt(index);
        } catch (NumberFormatException e) {

        }
        try {
            countInt = Integer.parseInt(count);
        } catch (NumberFormatException e) {

        }

        Date date1 = new Date(dateValue1);
        Date date2 = new Date(dateValue2);
        return this.converter
                .toDtoList(this.service.getAllByDateRange(city, date1, date2, "BETWEEN", indexInt, countInt));
    }
}
