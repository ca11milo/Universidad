package org.example.model;

import java.io.Serializable;

public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    private int ID;
    private String nombre;
    private Programa programa;
    private boolean activo;

    public Curso() {
        ID=0;
        nombre="";
        programa=null;
        activo=false;
    }

    public Curso(int ID, String nombre, Programa programa, boolean activo) {
        this.ID = ID;
        this.nombre = nombre;
        this.programa = programa;
        this.activo = activo;
    }

    public Curso(String nombre, Programa programa, boolean activo) {
        this.ID = 0;
        this.nombre = nombre;
        this.programa = programa;
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "ID=" + ID +
                ", programa=" + programa +
                ", activo=" + activo +
                '}';
    }
}
