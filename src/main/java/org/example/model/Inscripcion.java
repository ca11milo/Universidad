package org.example.model;

import java.io.Serializable;

public class Inscripcion implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Curso curso;
    private int año;
    private int semestre;
    private Estudiante estudiante;

    public Inscripcion(){
        id = 0;
        curso = null;
        año = 0;
        semestre = 0;
        estudiante = null;
    }

    public Inscripcion(Curso curso, int año, int semestre, Estudiante estudiante) {
        this.curso = curso;
        this.año = año;
        this.semestre = semestre;
        this.estudiante = estudiante;
    }
    public Inscripcion(int id, Curso curso, int año, int semestre, Estudiante estudiante) {
        this.id = id;
        this.curso = curso;
        this.año = año;
        this.semestre = semestre;
        this.estudiante = estudiante;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "curso=" + curso +
                ", año=" + año +
                ", semestre=" + semestre +
                ", estudiante=" + estudiante +
                '}';
    }
}
