package es.udc.rdopazo.tfg.app.model.persistence.api.event;

import java.util.Date;
import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public interface Event<ED extends EventDay<?, ?>> extends Entity<Long> {

    /**
     * Returns the id
     *
     * @return The id
     */
    public Long getId();

    /**
     * Sets the id to given value
     *
     * @param id
     *            The id to set
     */
    public void setId(Long id);

    /**
     * Returns the name
     *
     * @return The name
     */
    public String getName();

    /**
     * Sets the name to given value
     *
     * @param name
     *            The name to set
     */
    public void setName(String name);

    /**
     * Returns the description
     *
     * @return The description
     */
    public String getDescription();

    /**
     * Sets the description to given value
     *
     * @param description
     *            The description to set
     */
    public void setDescription(String description);

    /**
     * Returns the city
     *
     * @return The city
     */
    public String getCity();

    /**
     * Sets the city to given value
     *
     * @param city
     *            The city to set
     */
    public void setCity(String city);

    /**
     * Returns the startDate
     *
     * @return The startDate
     */
    public Date getStartDate();

    /**
     * Sets the startDate to given value
     *
     * @param startDate
     *            The startDate to set
     */
    public void setStartDate(Date startDate);

    /**
     * Returns the endDate
     *
     * @return The endDate
     */
    public Date getEndDate();

    /**
     * Sets the endDate to given value
     *
     * @param endDate
     *            The endDate to set
     */
    public void setEndDate(Date endDate);

    /**
     * Returns the days
     *
     * @return The days
     */
    public List<ED> getDays();

    /**
     * Sets the days to given value
     *
     * @param days
     *            The days to set
     */
    public void setDays(List<ED> days);

    public void addDay(ED day);

}
