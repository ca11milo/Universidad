package org.example.view.persona;

import org.example.controller.PersonaController;
import org.example.model.Persona;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GuardarPersona extends JPanel {
    private PersonaController personaController;
    private JTextField nombreField, apellidoField, emailField;

    public GuardarPersona(PersonaController personaController) {
        this.personaController = personaController;

        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("Crear Persona", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);

        JPanel formularioPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formularioPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        formularioPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formularioPanel.add(nombreField);

        formularioPanel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        formularioPanel.add(apellidoField);

        formularioPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formularioPanel.add(emailField);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> {
            try {
                guardarPersona();
            } catch (SQLException ex) {
                System.out.println("Error al guardar persona");
            }
        });

        JPanel botonPanel = new JPanel();
        botonPanel.add(guardarButton);

        add(formularioPanel, BorderLayout.CENTER);
        add(botonPanel, BorderLayout.SOUTH);
    }

    private void guardarPersona() throws SQLException {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String email = emailField.getText();

        personaController.guardarPersona(new Persona(0, nombre, apellido, email));
        JOptionPane.showMessageDialog(this, "Persona guardada exitosamente.");
    }
}
