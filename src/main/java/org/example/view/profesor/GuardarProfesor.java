package org.example.view.profesor;

import org.example.controller.ProfesorController;
import org.example.model.Profesor;
import org.example.view.ventanasCRUD.VentanaGuardar;

import java.sql.SQLException;

public class GuardarProfesor extends VentanaGuardar<Profesor> {
    private ProfesorController profesorController;

    public GuardarProfesor(ProfesorController profesorController) {
        super("Crear Profesor", new String[]{"Nombre", "Apellido", "Email", "TipoContrato"});
        this.profesorController = profesorController;
    }

    @Override
    protected void guardarEntidad() throws SQLException {
        String nombre = campos[0].getText();
        String apellido = campos[1].getText();
        String email = campos[2].getText();
        String tipoContrato = campos[3].getText();

        profesorController.guardarProfesor(new Profesor(0, nombre, apellido, email, tipoContrato));
    }
}
