package org.example.view.ventanas;

import org.example.controller.ProgramaController;

public class VentanaPrograma extends VentanaContenido {
    private ProgramaController programaController;
    public VentanaPrograma(ProgramaController programaController)
    {
        super("Programas");
        this.programaController = programaController;
    }
}
