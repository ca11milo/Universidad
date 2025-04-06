package org.example.view.persona;

import org.example.controller.PersonaController;
import org.example.model.Persona;
import org.example.model.factory.PersonaFactory;
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
        String idTexto = idField.getText().trim();
        String nombre = campos[0].getText().trim();
        String apellido = campos[1].getText().trim();
        String email = campos[2].getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo ID no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (nombre.isBlank() || apellido.isBlank() || email.isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Persona persona = PersonaFactory.crearPersona(id, nombre, apellido, email);
        personaController.actualizarPersona(persona);
        JOptionPane.showMessageDialog(this, "Persona actualizada exitosamente.");
        limpiarCampos();

    }
    @Override
    protected void cargarEntidad() {
        String idTexto = idField.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID antes de buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            Persona persona = personaController.obtenerPersonaPorId(id);
            if (persona != null) {
                campos[0].setText(persona.getNombres());
                campos[1].setText(persona.getApellidos());
                campos[2].setText(persona.getEmail());
            } else {
                JOptionPane.showMessageDialog(this, "Persona no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
