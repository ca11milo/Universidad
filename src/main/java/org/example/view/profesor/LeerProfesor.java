package org.example.view.profesor;

import org.example.controller.ProfesorController;
import org.example.model.Profesor;
import org.example.view.ventanasCRUD.VentanaLeer;

import java.util.List;

public class LeerProfesor extends VentanaLeer<Profesor> {
    private ProfesorController profesorController;

    public LeerProfesor(ProfesorController profesorController) {
        super("Profesores", new String[]{"ID", "Nombre", "Apellido", "Email", "Tipo de Contrato"});
        this.profesorController = profesorController;
        cargarDatos();
    }

    @Override
    protected List<Profesor> obtenerDatos() {
        return profesorController.obtenerListaProfesores();
    }

    @Override
    protected Object[] mapearFila(Profesor profesor) {
        return new Object[]{profesor.getID(), profesor.getNombres(), profesor.getApellidos(), profesor.getEmail(), profesor.getTipoContrato()};
    }
}