package org.example.model;

public class Estudiante extends Persona{
    private static final long serialVersionUID = 1L;
    private double codigo;
    private Programa programa;
    private Boolean activo;
    private double promedio;

    public Estudiante(){
        super();
        codigo = 0;
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

    public double getCodigo() {
        return codigo;
    }

    public void setCodigo(double codigo) {
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
