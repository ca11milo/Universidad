package org.example.view.persona;

import org.example.controller.PersonaController;
import org.example.view.ventanasCRUD.VentanaBorrar;

import javax.swing.*;
import java.sql.SQLException;

public class BorrarPersona extends VentanaBorrar {
    private PersonaController personaController;

    public BorrarPersona(PersonaController personaController) {
        super("Persona");
        this.personaController = personaController;
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
            boolean eliminado = personaController.eliminarPersona(id);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Persona eliminada exitosamente.");
                idField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la persona con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
