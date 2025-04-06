package org.example.view.facultad;

import org.example.controller.FacultadController;
import org.example.model.Facultad;
import org.example.view.ventanasCRUD.VentanaLeer;

import java.util.List;

public class LeerFacultad extends VentanaLeer<Facultad> {
    private FacultadController facultadController;

    public LeerFacultad(FacultadController facultadController) {
        super("Facultad", new String[]{"ID", "Nombre", "Decano"});
        this.facultadController= facultadController;
        cargarDatos();
    }

    @Override
    protected List<Facultad> obtenerDatos() {
        return facultadController.obtenerListaFaculdades();
    }

    @Override
    protected Object[] mapearFila(Facultad facultad) {
        return new Object[]{facultad.getID(), facultad.getNombre(), facultad.getDecano().getNombreCompleto()};
    }
}

