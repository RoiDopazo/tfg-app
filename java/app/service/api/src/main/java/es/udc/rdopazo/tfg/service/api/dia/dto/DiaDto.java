package es.udc.rdopazo.tfg.service.api.dia.dto;

import java.util.List;

import es.udc.rdopazo.tfg.service.api.dialugar.DiaLugarDto;
import es.udc.rdopazo.tfg.service.api.ruta.dto.RutaDto;
import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class DiaDto implements EntityDto {

    private Long idRoute;

    private Long idDay;

    private String name;

    private Long order;

    private List<DiaLugarDto> dayPlaces;

    private RutaDto route;

    /**
     * Returns the idRoute
     *
     * @return The idRoute
     */
    public Long getIdRoute() {
        return this.idRoute;
    }

    /**
     * Sets the idRoute to given value
     *
     * @param idRoute
     *            The idRoute to set
     */
    public void setIdRoute(Long idRoute) {
        this.idRoute = idRoute;
    }

    /**
     * Returns the idDay
     *
     * @return The idDay
     */
    public Long getIdDay() {
        return this.idDay;
    }

    /**
     * Sets the idDay to given value
     *
     * @param idDay
     *            The idDay to set
     */
    public void setIdDay(Long idDay) {
        this.idDay = idDay;
    }

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

    /**
     * Returns the places
     *
     * @return The places
     */
    public List<DiaLugarDto> getPlaces() {
        return this.dayPlaces;
    }

    /**
     * Sets the places to given value
     *
     * @param places
     *            The places to set
     */
    public void setPlaces(List<DiaLugarDto> dayPlaces) {
        this.dayPlaces = dayPlaces;
    }

    /**
     * Returns the route
     *
     * @return The route
     */
    public RutaDto getRoute() {
        return this.route;
    }

    /**
     * Sets the route to given value
     *
     * @param route
     *            The route to set
     */
    public void setRoute(RutaDto route) {
        this.route = route;
    }

}
