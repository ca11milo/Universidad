package org.example.view.programa;

import org.example.controller.ProgramaController;
import org.example.view.ventanasCRUD.VentanaBorrar;
import javax.swing.*;
import java.sql.SQLException;

public class BorrarPrograma extends VentanaBorrar {
    private ProgramaController programaController;

    public BorrarPrograma(ProgramaController profesorController) {
        super("Programa");
        this.programaController = programaController;
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
            boolean eliminado = programaController.eliminarPrograma(id);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Programa eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el Programa. Es posible que esté relacionado con otros datos.\"", "Error", JOptionPane.ERROR_MESSAGE);
            }
            idField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
