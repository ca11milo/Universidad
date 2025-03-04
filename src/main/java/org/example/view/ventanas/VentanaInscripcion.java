package org.example.view.ventanas;

import org.example.controller.InscripcionController;

public class VentanaInscripcion extends VentanaContenido {
    private InscripcionController inscripcionController;

    public VentanaInscripcion(InscripcionController inscripcionController)
    {
        super("Inscripciones");
        this.inscripcionController = inscripcionController;
    }
}
