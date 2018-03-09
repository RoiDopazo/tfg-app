package es.udc.rdopazo.tfg.service.api.route.day.dto;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class RouteDayPersistDto implements EntityDto {

    private Long idRoute;

    private Long idDay;

    private Long startTime;

    private String realTimeData;

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
     * Returns the startTime
     *
     * @return The startTime
     */
    public Long getStartTime() {
        return this.startTime;
    }

    /**
     * Sets the startTime to given value
     *
     * @param startTime
     *            The startTime to set
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the realTimeData
     *
     * @return The realTimeData
     */
    public String getRealTimeData() {
        return this.realTimeData;
    }

    /**
     * Sets the realTimeData to given value
     *
     * @param realTimeData
     *            The realTimeData to set
     */
    public void setRealTimeData(String realTimeData) {
        this.realTimeData = realTimeData;
    }

}
