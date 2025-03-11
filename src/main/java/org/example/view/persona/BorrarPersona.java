package org.example.view.persona;

import org.example.controller.PersonaController;
import org.example.view.ventanasCRUD.VentanaBorrar;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class BorrarPersona extends VentanaBorrar {
    private PersonaController personaController;

    public BorrarPersona(PersonaController personaController) {
        super("Persona");
        this.personaController = personaController;
    }

    @Override
    protected void eliminar() throws SQLException {
        try {
            int id = Integer.parseInt(idField.getText());
            String mensajeEliminada = personaController.eliminarPersona(id);
            System.out.println(mensajeEliminada);
            idField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
