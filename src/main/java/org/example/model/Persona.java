package org.example.model;

import java.io.Serializable;

public class Persona implements Serializable{
    private static final long serialVersionUID = 1L;

    private double ID;
    private String nombres;
    private String apellidos;
    private String email;

    public Persona(String nombres, String email, String apellidos, double ID) {
        this.nombres = nombres;
        this.email = email;
        this.apellidos = apellidos;
        this.ID = ID;
    }

    public double getID() {
        return ID;
    }

    public void setID(double ID) {
        this.ID = ID;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID=" + ID +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'';
    }
}
