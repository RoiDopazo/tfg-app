package es.udc.rdopazo.tfg.etravel.api.example;

import java.io.Serializable;

public class ExampleDto implements Serializable {

    private static final long serialVersionUID = 7830797550493453101L;

    private String nombre;

    private int edad;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}
