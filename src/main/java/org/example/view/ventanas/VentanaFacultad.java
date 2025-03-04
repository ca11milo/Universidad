package org.example.view.ventanas;

import org.example.controller.FacultadController;

public class VentanaFacultad extends VentanaContenido {
    private FacultadController facultadController;
    public VentanaFacultad(FacultadController facultadController)
    {
        super("Facultades");
        this.facultadController = facultadController;
    }
}
