package es.udc.rdopazo.tfg.app.util.exceptions;

@SuppressWarnings("serial")
public class InstanceNotFoundException extends Exception {

    private Object instanceId;
    private String instanceType;

    public InstanceNotFoundException(Object instanceId, String instanceType) {

        super("Instance not found (identifier = '" + instanceId + "' - type = '" + instanceType + "')");
        this.instanceId = instanceId;
        this.instanceType = instanceType;

    }

    public Object getInstanceId() {
        return this.instanceId;
    }

    public String getInstanceType() {
        return this.instanceType;
    }
}
