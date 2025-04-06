package org.example.view.persona;

import org.example.controller.PersonaController;
import org.example.model.Persona;
import org.example.model.factory.PersonaFactory;
import org.example.view.ventanasCRUD.VentanaGuardar;

import javax.swing.*;
import java.sql.SQLException;

public class GuardarPersona extends VentanaGuardar<Persona> {
    private PersonaController personaController;

    public GuardarPersona(PersonaController personaController) {
        super("Crear Persona", new String[]{"Nombre", "Apellido", "Email"});
        this.personaController = personaController;
    }

    @Override
    protected void guardarEntidad() throws SQLException {
        String nombre = campos[0].getText().trim();
        String apellido = campos[1].getText().trim();
        String email = campos[2].getText().trim();

        if (nombre.isBlank() || apellido.isBlank() || email.isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Persona persona = PersonaFactory.crearPersona(0, nombre, apellido, email);
        personaController.guardarPersona(persona);

        JOptionPane.showMessageDialog(this, "Persona guardada exitosamente.");
        limpiarCampos();
    }

}
