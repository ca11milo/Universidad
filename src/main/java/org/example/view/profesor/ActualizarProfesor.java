package org.example.view.profesor;

import org.example.controller.ProfesorController;
import org.example.model.Profesor;
import org.example.model.factory.PersonaFactory;
import org.example.view.ventanasCRUD.VentanaActualizar;

import javax.swing.*;
import java.sql.SQLException;

public class ActualizarProfesor extends VentanaActualizar<Profesor> {
    private ProfesorController profesorController;

    public ActualizarProfesor(ProfesorController profesorController) {
        super("Actualizar Profesor", new String[]{"Nombre", "Apellidos", "Email", "Tipo de Contrato"});
        this.profesorController = profesorController;
    }

    @Override
    protected void guardarEntidad() throws SQLException {
        String idTexto = idField.getText().trim();

        int id;
        try {
            id = Integer.parseInt(idTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = campos[0].getText();
        String apellido = campos[1].getText();
        String email = campos[2].getText();
        String tipoContrato = campos[3].getText();

        if (nombre.isBlank() || apellido.isBlank() || email.isBlank() || tipoContrato.isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Profesor profesor = PersonaFactory.crearProfesor(id, nombre, apellido, email, tipoContrato);
        profesorController.actualizarProfesor(profesor);
        JOptionPane.showMessageDialog(this, "Profesor actualizado exitosamente.");
    }


    @Override
    protected void cargarEntidad() {
        String idTexto = idField.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo ID no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            Profesor profesor = profesorController.obtenerProfesorPorId(id);
            if (profesor != null) {
                campos[0].setText(profesor.getNombres());
                campos[1].setText(profesor.getApellidos());
                campos[2].setText(profesor.getEmail());
                campos[3].setText(profesor.getTipoContrato());
            } else {
                JOptionPane.showMessageDialog(this, "Profesor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
