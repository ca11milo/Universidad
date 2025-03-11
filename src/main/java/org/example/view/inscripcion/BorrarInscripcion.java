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
                idField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la inscripción con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
