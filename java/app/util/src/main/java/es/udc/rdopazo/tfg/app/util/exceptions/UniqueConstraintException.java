package es.udc.rdopazo.tfg.app.util.exceptions;

@SuppressWarnings("serial")
public class UniqueConstraintException extends Exception {

    private Object instanceId;
    private String instanceType;
    private String instanceValue;

    public UniqueConstraintException(String instanceType, String instanceValue) {

        super("UniqueConstraint -- " + instanceType + " with value " + instanceValue + " is already registered");
        this.instanceId = "UniqueConstraint";
        this.instanceType = instanceType;
        this.instanceValue = instanceValue;

    }

    public Object getInstanceId() {
        return this.instanceId;
    }

    public String getInstanceType() {
        return this.instanceType;
    }

    public String getInstanceValue() {
        return this.instanceValue;
    }
}
