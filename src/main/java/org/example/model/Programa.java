package org.example.model;

import java.io.Serializable;
import java.util.Date;

public class Programa implements Serializable {
    private static final long serialVersionUID = 1L;
    private int ID;
    private String nombre;
    private Double duracion;
    private Date registro;
    private Facultad facultad;

    public Programa(){
        ID = 0;
        nombre = "";
        duracion = 0.0;
        registro = null;
        facultad = null;
    }

    public Programa(int ID){
        this.ID = ID;
        nombre = "";
        duracion = 0.0;
        registro = null;
        facultad = null;
    }

    public Programa(String nombre, Date registro, double duracion, Facultad facultad) {
        this.ID = 0;
        this.nombre = nombre;
        this.registro = registro;
        this.duracion = duracion;
        this.facultad = facultad;
    }

    public Programa(int ID, String nombre, Date registro, double duracion, Facultad facultad) {
        this.ID = ID;
        this.nombre = nombre;
        this.registro = registro;
        this.duracion = duracion;
        this.facultad = facultad;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
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
