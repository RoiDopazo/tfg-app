package es.udc.rdopazo.tfg.app.util.exceptions.dto;

public class InstanceNotFoundExceptionDto {
    private String instanceId;
    private String instanceType;

    public InstanceNotFoundExceptionDto(Object instanceId, String instanceType) {
        this.instanceId = instanceId.toString();
        this.instanceType = instanceType;
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
}
