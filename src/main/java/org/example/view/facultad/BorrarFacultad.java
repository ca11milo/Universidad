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
                idField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la facultad con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}