package org.example.model;

import java.io.Serializable;

public class Facultad implements Serializable {
    private static final long serialVersionUID = 1L;
    private int ID;
    private String nombre;
    private Persona decano;

    public Facultad() {
        ID = 0;
        nombre = "";
        decano = null;
    }

    public Facultad(int ID, String nombre, Persona decano) {
        this.ID = ID;
        this.nombre = nombre;
        this.decano = decano;
    }

    public Facultad(String nombre, Persona decano) {
        this.ID = 0;
        this.nombre = nombre;
        this.decano = decano;
    }

    public Persona getDecano() {
        return decano;
    }

    public void setDecano(Persona decano) {
        this.decano = decano;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Facultad{" +
                "ID=" + ID +
                ", nombre='" + nombre + '\'' +
                ", decano=" + decano +
                '}';
    }
}
