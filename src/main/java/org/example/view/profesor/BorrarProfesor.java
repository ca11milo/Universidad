package org.example.view.profesor;

import org.example.controller.ProfesorController;
import org.example.view.ventanasCRUD.VentanaBorrar;
import javax.swing.*;
import java.sql.SQLException;

public class BorrarProfesor extends VentanaBorrar {
    private ProfesorController profesorController;

    public BorrarProfesor(ProfesorController profesorController) {
        super("Profesor");
        this.profesorController = profesorController;
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
            boolean eliminado = profesorController.eliminarProfesor(id);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Profesor eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el Profesor. Es posible que esté relacionado con otros datos.\"", "Error", JOptionPane.ERROR_MESSAGE);
            }
            idField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
