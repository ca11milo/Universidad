package org.example.model;

import java.io.Serializable;

public class CursoProfesor implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Profesor profesor;
    private int año;
    private int semestre;
    private Curso curso;

    public CursoProfesor() {
        id = 0;
        profesor = null;
        año = 0;
        semestre = 0;
        curso = null;
    }
    public CursoProfesor(int id) {
        this.id = id;
        profesor = null;
        año = 0;
        semestre = 0;
        curso = null;
    }

    public CursoProfesor(Profesor profesor, int semestre, int año, Curso curso) {
        this.profesor = profesor;
        this.semestre = semestre;
        this.año = año;
        this.curso = curso;
    }
    public CursoProfesor(int id, Profesor profesor, int semestre, int año, Curso curso) {
        this.id=id;
        this.profesor = profesor;
        this.semestre = semestre;
        this.año = año;
        this.curso = curso;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "CursoProfesor{" +
                "profesor=" + profesor +
                ", año=" + año +
                ", semestre=" + semestre +
                ", curso=" + curso +
                '}';
    }
}
