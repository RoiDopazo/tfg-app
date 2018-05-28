package es.udc.rdopazo.tfg.app.util.exceptions.dto;

public class UnUpdateableRouteExceptionDto {
    private String instanceId;
    private String instanceType;
    private String instanceValue;

    public UnUpdateableRouteExceptionDto(Object instanceId, String instanceType, String instanceValue) {
        this.instanceId = instanceId.toString();
        this.instanceType = instanceType;
        this.instanceValue = instanceValue;
    }

    public String getInstanceId() {
        return this.instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceType() {
        return this.instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    /**
     * Returns the instanceValue
     * 
     * @return The instanceValue
     */
    public String getInstanceValue() {
        return this.instanceValue;
    }

    /**
     * Sets the instanceValue to given value
     * 
     * @param instanceValue
     *            The instanceValue to set
     */
    public void setInstanceValue(String instanceValue) {
        this.instanceValue = instanceValue;
    }

}
