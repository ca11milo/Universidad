package org.example.model;

import java.io.Serializable;

public class Estudiante extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private Double codigo;
    private Programa programa;
    private Boolean activo;
    private double promedio;

    public Estudiante(){
        super();
        codigo = 0.0;
        programa = null;
        activo = false;
        promedio = 0;
    }

    public Estudiante(int id){
        super(id);
        codigo = 0.0;
        programa = null;
        activo = false;
        promedio = 0;
    }

    public Estudiante(String nombres, String apellidos, String email, double codigo, Programa programa, Boolean activo, double promedio) {
        super(nombres, apellidos, email);
        this.codigo = codigo;
        this.programa = programa;
        this.promedio = promedio;
        this.activo = activo;
    }
    public Estudiante(int ID, String nombres, String apellidos, String email, double codigo, Programa programa, Boolean activo, double promedio) {
        super(ID, nombres, apellidos, email);
        this.codigo = codigo;
        this.programa = programa;
        this.promedio = promedio;
        this.activo = activo;
    }

    public Double getCodigo() {
        return codigo;
    }

    public void setCodigo(Double codigo) {
        this.codigo = codigo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "Estudiante: " +
                super.toString() + '\''+
                "codigo=" + codigo + '\''+
                ", programa=" + programa + '\''+
                ", activo=" + activo + '\''+
                ", promedio=" + promedio + '\'';
    }
}
