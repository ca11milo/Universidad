package org.example.view.persona;

import org.example.controller.PersonaController;
import org.example.model.Persona;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ActualizarPersona extends JPanel {
    private PersonaController personaController;
    private JTextField nombreField, apellidoField, emailField, idField;

    public ActualizarPersona(PersonaController personaController) {
        this.personaController = personaController;

        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("PERSONA", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tituloLabel.setOpaque(true);
        tituloLabel.setBackground(Color.BLUE);
        tituloLabel.setForeground(Color.WHITE);
        add(tituloLabel, BorderLayout.NORTH);

        JPanel formularioPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formularioPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        formularioPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formularioPanel.add(nombreField);

        formularioPanel.add(new JLabel("Apellidos:"));
        apellidoField = new JTextField();
        formularioPanel.add(apellidoField);

        formularioPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formularioPanel.add(emailField);

        formularioPanel.add(new JLabel("Ingresa el ID de la persona que deseas actualizar"));
        idField = new JTextField();
        formularioPanel.add(idField);

        JPanel botonPanel = new JPanel();
        JButton guardarButton = new JButton("Guardar");
        JButton cancelarButton = new JButton("Cancelar");
        JButton cargarButton = new JButton("Cargar");

        guardarButton.addActionListener(e -> {
            try {
                guardarPersona();
            } catch (SQLException ex) {
                System.out.println("Error al guardar persona");
            }
        });

        cargarButton.addActionListener(e -> cargarPersona());

        botonPanel.add(cargarButton);
        botonPanel.add(guardarButton);
        botonPanel.add(cancelarButton);

        add(formularioPanel, BorderLayout.CENTER);
        add(botonPanel, BorderLayout.SOUTH);
    }

    private void guardarPersona() throws SQLException {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String email = emailField.getText();
        int id = Integer.parseInt(idField.getText());

        personaController.actualizarPersona(new Persona(id, nombre, apellido, email));
        JOptionPane.showMessageDialog(this, "Persona actualizada exitosamente.");
    }

    private void cargarPersona() {
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
