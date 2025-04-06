package org.example.view.inscripcion;

import org.example.controller.InscripcionController;
import org.example.view.ventanasCRUD.VentanaBorrar;

import javax.swing.*;
import java.sql.SQLException;

public class BorrarInscripcion extends VentanaBorrar {
    private InscripcionController inscripcionController;

    public BorrarInscripcion(InscripcionController inscripcionController) {
        super("Inscripción");
        this.inscripcionController = inscripcionController;
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
            boolean eliminado = inscripcionController.eliminarInscripcion(id);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Inscripción eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar al inscripcion. Es posible que esté relacionado con otros datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            idField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
