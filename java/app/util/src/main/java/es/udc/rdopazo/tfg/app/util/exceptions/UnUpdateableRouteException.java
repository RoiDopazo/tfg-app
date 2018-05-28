package es.udc.rdopazo.tfg.app.util.exceptions;

@SuppressWarnings("serial")
public class UnUpdateableRouteException extends Exception {

    private Object instanceId;
    private String instanceType;
    private String instanceValue;

    public UnUpdateableRouteException(String instanceType, String instanceValue) {

        super("UnUpdateableRouteException -- " + instanceType + " with state " + instanceValue + " cant be updatebale");
        this.instanceId = "UnUpdateableRouteException";
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
