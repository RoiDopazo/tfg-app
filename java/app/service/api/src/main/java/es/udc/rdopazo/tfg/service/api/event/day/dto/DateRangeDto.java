package es.udc.rdopazo.tfg.service.api.event.day.dto;

import java.util.Date;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class DateRangeDto implements EntityDto {

    private static final long serialVersionUID = -37147793425125216L;

    private Date start_date;

    private Date end_date;

    private String type;

    /**
     * Returns the start_date
     *
     * @return The start_date
     */
    public Date getStart_date() {
        return this.start_date;
    }

    /**
     * Sets the start_date to given value
     *
     * @param start_date
     *            The start_date to set
     */
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    /**
     * Returns the end_date
     *
     * @return The end_date
     */
    public Date getEnd_date() {
        return this.end_date;
    }

    /**
     * Sets the end_date to given value
     *
     * @param end_date
     *            The end_date to set
     */
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    /**
     * Returns the type
     * 
     * @return The type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the type to given value
     * 
     * @param type
     *            The type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
