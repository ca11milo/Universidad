package org.example.view.programa;

import org.example.controller.ProfesorController;
import org.example.controller.ProgramaController;
import org.example.model.Programa;
import org.example.view.ventanasCRUD.VentanaLeer;

import java.util.List;

public class LeerPrograma extends VentanaLeer<Programa> {
    private ProgramaController programaController;

    public LeerPrograma(ProgramaController programaController) {
        super("Programas", new String[]{"ID", "Nombre", "Duración", "Registro", "Id Facultad"});
        this.programaController = programaController;
        cargarDatos();
    }

    @Override
    protected List<Programa> obtenerDatos() {
        return programaController.obtenerListaProgramas();
    }

    @Override
    protected Object[] mapearFila(Programa programa) {
        return new Object[]{programa.getID(), programa.getNombre(), programa.getDuracion(), programa.getRegistro(), programa.getFacultad().getID()};
    }
}