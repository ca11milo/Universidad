package org.example.view.ventanas;

import org.example.controller.CursoProfesorController;

public class VentanaCursoProfesor extends VentanaContenido {
    private CursoProfesorController cursoProfesorController;
    public VentanaCursoProfesor(CursoProfesorController cursoProfesorController)
    {
        super("Curso-Profesor");
        this.cursoProfesorController = cursoProfesorController ;

    }
}
