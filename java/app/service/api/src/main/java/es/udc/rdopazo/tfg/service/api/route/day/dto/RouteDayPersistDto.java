package es.udc.rdopazo.tfg.service.api.route.day.dto;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class RouteDayPersistDto implements EntityDto {

    private String name;

    private Long order;

    /**
     * Returns the name
     *
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name to given value
     *
     * @param name
     *            The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the order
     *
     * @return The order
     */
    public Long getOrder() {
        return this.order;
    }

    /**
     * Sets the order to given value
     *
     * @param order
     *            The order to set
     */
    public void setOrder(Long order) {
        this.order = order;
    }

}
