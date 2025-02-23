package org.example;

import java.io.Serializable;
import java.util.Date;

public class Programa implements Serializable {
    private static final long serialVersionUID = 1L;
    private double ID;
    private String nombre;
    private double duracion;
    private Date registro;
    private Facultad facultad;

    public Programa(double ID, String nombre, Date registro, double duracion, Facultad facultad) {
        this.ID = ID;
        this.nombre = nombre;
        this.registro = registro;
        this.duracion = duracion;
        this.facultad = facultad;
    }

    public double getID() {
        return ID;
    }

    public void setID(double ID) {
        this.ID = ID;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Date getRegistro() {
        return registro;
    }

    public void setRegistro(Date registro) {
        this.registro = registro;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Programa{" +
                "ID=" + ID +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", registro='" + registro + '\'' +
                ", facultad=" + facultad +
                '}';
    }
}
