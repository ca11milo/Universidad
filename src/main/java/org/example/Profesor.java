package org.example;

public class Profesor extends Persona{

    private String tipoContrato;

    public Profesor(String nombres, String email, String apellidos, double ID, String tipoContrato) {
        super(nombres, email, apellidos, ID);
        this.tipoContrato = tipoContrato;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    @Override
    public String toString() {
        return  "Profesor: " + '\'' +
                super.toString() +
                "tipoContrato='" + tipoContrato + '\'';
    }
}
