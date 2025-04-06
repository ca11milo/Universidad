package org.example.view.facultad;

import org.example.controller.CursoController;
import org.example.controller.FacultadController;
import org.example.view.ventanasCRUD.VentanaBorrar;

import javax.swing.*;
import java.sql.SQLException;

public class BorrarFacultad extends VentanaBorrar {
    private FacultadController facultadController;

    public BorrarFacultad(FacultadController facultadController) {
        super("Facultad");
        this.facultadController = facultadController;
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
            boolean eliminado = facultadController.eliminarFacultad(id);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Facultad eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar la facultad. Es posible que esté relacionado con otros datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            idField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}