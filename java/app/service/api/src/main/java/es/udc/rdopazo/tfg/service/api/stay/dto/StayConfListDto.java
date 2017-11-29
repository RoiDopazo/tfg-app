package es.udc.rdopazo.tfg.service.api.stay.dto;

import java.util.List;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class StayConfListDto implements EntityDto {

    private static final long serialVersionUID = 5445258586508977181L;

    private List<Long> daysBefore;

    private List<Long> daysAfter;

    private StayPlaceDto stay;

    /**
     * Returns the daysBefore
     *
     * @return The daysBefore
     */
    public List<Long> getDaysBefore() {
        return this.daysBefore;
    }

    /**
     * Sets the daysBefore to given value
     *
     * @param daysBefore
     *            The daysBefore to set
     */
    public void setDaysBefore(List<Long> daysBefore) {
        this.daysBefore = daysBefore;
    }

    /**
     * Returns the daysAfter
     *
     * @return The daysAfter
     */
    public List<Long> getDaysAfter() {
        return this.daysAfter;
    }

    /**
     * Sets the daysAfter to given value
     *
     * @param daysAfter
     *            The daysAfter to set
     */
    public void setDaysAfter(List<Long> daysAfter) {
        this.daysAfter = daysAfter;
    }

    /**
     * Returns the stay
     *
     * @return The stay
     */
    public StayPlaceDto getStay() {
        return this.stay;
    }

    /**
     * Sets the stay to given value
     *
     * @param stay
     *            The stay to set
     */
    public void setStay(StayPlaceDto stay) {
        this.stay = stay;
    }

}
