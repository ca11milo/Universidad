package org.example.view.profesor;

import org.example.controller.ProfesorController;
import org.example.model.Profesor;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GuardarProfesor extends JPanel {
    private ProfesorController profesorController;
    private JTextField nombreField, apellidoField, emailField, tipoContratoField;

    public GuardarProfesor(ProfesorController profesorController) {
        this.profesorController = profesorController;

        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("Crear Profesor", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);

        JPanel formularioPanel = new JPanel(new GridLayout(4, 2, 10, 10));
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

        formularioPanel.add(new JLabel("Tipo de Contrato:"));
        tipoContratoField = new JTextField();
        formularioPanel.add(tipoContratoField);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> {
            try {
                guardarProfesor();
            } catch (SQLException ex) {
                System.out.println("Error al guardar profesor");
            }
        });

        JPanel botonPanel = new JPanel();
        botonPanel.add(guardarButton);

        add(formularioPanel, BorderLayout.CENTER);
        add(botonPanel, BorderLayout.SOUTH);
    }

    private void guardarProfesor() throws SQLException {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String email = emailField.getText();
        String tipoContrato = tipoContratoField.getText();

        profesorController.guardarProfesor(new Profesor(0, nombre, apellido, email, tipoContrato));
        JOptionPane.showMessageDialog(this, "Profesor guardado exitosamente.");
    }
}
