package es.udc.rdopazo.tfg.service.api.dialugar.dto;

import java.util.List;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class DiaLugarConfListDto implements EntityDto {

    private List<Long> daysBefore;

    private List<Long> daysAfter;

    private DiaLugarDto diaLugar;

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
     * Returns the diaLugar
     * 
     * @return The diaLugar
     */
    public DiaLugarDto getDiaLugar() {
        return this.diaLugar;
    }

    /**
     * Sets the diaLugar to given value
     * 
     * @param diaLugar
     *            The diaLugar to set
     */
    public void setDiaLugar(DiaLugarDto diaLugar) {
        this.diaLugar = diaLugar;
    }

}
