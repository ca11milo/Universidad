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
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el estudiante. Es posible que esté relacionado con otros datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            idField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
