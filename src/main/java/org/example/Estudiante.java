package org.example;

public class Estudiante extends Persona {
    private double codigo;
    private Programa programa;
    private Boolean activo;
    private double promedio;

    public Estudiante(String nombres, String email, String apellidos, double ID, double codigo, Programa programa, double promedio, Boolean activo) {
        super(nombres, email, apellidos, ID);
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
