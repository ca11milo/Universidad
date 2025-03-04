package org.example.model;

import java.io.Serializable;

public class Persona implements Serializable{
    private static final long serialVersionUID = 1L;

    private int ID;
    private String nombres;
    private String apellidos;
    private String email;

    public Persona() {
        ID = 0;
        nombres = "";
        apellidos = "";
        email = "";
    }
    public Persona(int ID) {
        this.ID = ID;
        nombres = "";
        apellidos = "";
        email = "";
    }

    public Persona(String nombres, String apellidos, String email) {
        this.nombres = nombres;
        this.email = email;
        this.apellidos = apellidos;
        this.ID = 0;
    }

    public Persona(int ID, String nombres, String apellidos, String email) {
        this.ID = ID;
        this.nombres = nombres;
        this.email = email;
        this.apellidos = apellidos;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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
