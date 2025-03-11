package org.example.view.estudiante;

import org.example.controller.EstudianteController;
import org.example.view.ventanasCRUD.VentanaBorrar;

import javax.swing.*;
import java.sql.SQLException;

public class BorrarEstudiante extends VentanaBorrar {

    private EstudianteController estudianteController;

    public BorrarEstudiante(EstudianteController estudianteController) {
        super("Curso");
        this.estudianteController = estudianteController;
    }

    @Override
    protected void eliminar() throws SQLException {
        String idTexto = idField.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            boolean eliminado = estudianteController.eliminarEstudiante(id);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Estudiante eliminada exitosamente.");
                idField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el estudiante con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
