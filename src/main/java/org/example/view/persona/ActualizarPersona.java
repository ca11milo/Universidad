package org.example.view.persona;

import org.example.controller.PersonaController;
import org.example.model.Persona;
import org.example.view.ventanasCRUD.VentanaActualizar;

import javax.swing.*;
import java.sql.SQLException;

public class ActualizarPersona extends VentanaActualizar<Persona> {
    private PersonaController personaController;
    private JTextField nombreField, apellidoField, emailField;

    public ActualizarPersona(PersonaController personaController) {
        super("Persona");
        this.personaController = personaController;
    }

    @Override
    protected void agregarCampos() {
        formularioPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formularioPanel.add(nombreField);

        formularioPanel.add(new JLabel("Apellidos:"));
        apellidoField = new JTextField();
        formularioPanel.add(apellidoField);

        formularioPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formularioPanel.add(emailField);
    }

    @Override
    protected void guardarEntidad() throws SQLException {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String email = emailField.getText();
        int id = Integer.parseInt(idField.getText());

        personaController.actualizarPersona(new Persona(id, nombre, apellido, email));
        JOptionPane.showMessageDialog(this, "Persona actualizada exitosamente.");
    }

    @Override
    protected void cargarEntidad() {
        try {
            int id = Integer.parseInt(idField.getText());
            Persona persona = personaController.obtenerPersonaPorId(id);
            if (persona != null) {
                nombreField.setText(persona.getNombres());
                apellidoField.setText(persona.getApellidos());
                emailField.setText(persona.getEmail());
            } else {
                JOptionPane.showMessageDialog(this, "Persona no encontrada.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.");
        }
    }
}
