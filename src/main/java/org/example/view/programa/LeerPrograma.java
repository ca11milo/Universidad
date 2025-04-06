package org.example.view.programa;

import org.example.controller.ProgramaController;
import org.example.model.Programa;
import org.example.patterns.observer.Observer;
import org.example.view.ventanasCRUD.VentanaLeer;

import java.util.List;

public class LeerPrograma extends VentanaLeer<Programa> implements Observer {
    private ProgramaController programaController;

    public LeerPrograma(ProgramaController programaController) {
        super("Programas", new String[]{"ID", "Nombre", "Duración", "Registro", "Facultad"});
        this.programaController = programaController;
        programaController.agregarObservador(this);
        cargarDatos();
    }

    @Override
    protected List<Programa> obtenerDatos() {
        return programaController.obtenerListaProgramas();
    }

    @Override
    protected Object[] mapearFila(Programa programa) {
        return new Object[]{programa.getID(), programa.getNombre(), programa.getDuracion().intValue(), programa.getRegistro(), programa.getFacultad().getNombre()};
    }

    @Override
    public void actualizar() {
        cargarDatos();
    }
}