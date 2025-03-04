package org.example.view.ventanas;

import org.example.controller.EstudianteController;

public class VentanaEstudiante extends VentanaContenido {
    private EstudianteController estudianteController;

    public VentanaEstudiante(EstudianteController estudianteController) {
        super("Estudiantes");
        this.estudianteController = estudianteController;
    }
}
