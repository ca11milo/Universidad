package org.example.view.ventanas;

import org.example.controller.CursoController;

public class VentanaCurso extends VentanaContenido {

    private CursoController cursoController;

    public VentanaCurso(CursoController cursoController)
    {
        super("Cursos");
        this.cursoController = cursoController;
    }
}
