package org.example.model;

public class Profesor extends Persona{

    private String tipoContrato;

    public Profesor() {
        super();
        tipoContrato = "";
    }

    public Profesor(int ID) {
        super(ID);
        tipoContrato = "";
    }

    public Profesor(String nombres, String apellidos, String email, String tipoContrato) {
        super(nombres, apellidos, email);
        this.tipoContrato = tipoContrato;
    }

    public Profesor(int ID, String nombres, String apellidos, String email, String tipoContrato) {
        super(ID, nombres, apellidos, email);
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
