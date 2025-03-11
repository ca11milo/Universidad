package org.example.view.profesor;

import org.example.controller.ProfesorController;
import org.example.model.Profesor;
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
        int id = Integer.parseInt(idField.getText());
        String nombre = campos[0].getText();
        String apellido = campos[1].getText();
        String email = campos[2].getText();
        String tipoContrato = campos[3].getText();

        profesorController.actualizarProfesor(new Profesor(id, nombre, apellido, email, tipoContrato));
        JOptionPane.showMessageDialog(this, "Profesor actualizado exitosamente.");
    }

    @Override
    protected void cargarEntidad() {
        try {
            int id = Integer.parseInt(idField.getText()); // Se usa el campo correcto para el ID
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
