package org.example.view.persona;

import org.example.controller.PersonaController;
import org.example.model.Persona;
import org.example.view.ventanasCRUD.VentanaActualizar;

import javax.swing.*;
import java.sql.SQLException;

public class ActualizarPersona extends VentanaActualizar<Persona> {
    private PersonaController personaController;

    public ActualizarPersona(PersonaController personaController) {
        super("Actualizar Persona", new String[]{"Nombre", "Apellidos", "Email"});
        this.personaController = personaController;
    }

    @Override
    protected void guardarEntidad() throws SQLException {
        int id = Integer.parseInt(idField.getText());
        String nombre = campos[0].getText();
        String apellido = campos[1].getText();
        String email = campos[2].getText();

        personaController.actualizarPersona(new Persona(id, nombre, apellido, email));
        JOptionPane.showMessageDialog(this, "Persona actualizada exitosamente.");
    }

    @Override
    protected void cargarEntidad() {
        try {
            int id = Integer.parseInt(idField.getText());
            Persona persona = personaController.obtenerPersonaPorId(id);
            if (persona != null) {
                campos[0].setText(persona.getNombres());
                campos[1].setText(persona.getApellidos());
                campos[2].setText(persona.getEmail());
            } else {
                JOptionPane.showMessageDialog(this, "Persona no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
