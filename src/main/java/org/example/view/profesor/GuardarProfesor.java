package org.example.view.profesor;

import org.example.controller.ProfesorController;
import org.example.model.Profesor;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GuardarProfesor extends JPanel {
    private ProfesorController profesorController;
    private JTextField nombreField, apellidoField, emailField, tipoField;

    public GuardarProfesor(ProfesorController profesorController) {
        this.profesorController = profesorController;

        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("Registrar Profesor", SwingConstants.CENTER);
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

        formularioPanel.add(new JLabel("Tipo de Contrato:"));
        tipoField = new JTextField();
        formularioPanel.add(tipoField);

        JPanel botonPanel = new JPanel();
        JButton guardarButton = new JButton("Guardar");
        JButton cancelarButton = new JButton("Cancelar");

        guardarButton.addActionListener(e -> {
            try {
                guardarProfesor();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar profesor.");
            }
        });

        botonPanel.add(guardarButton);
        botonPanel.add(cancelarButton);

        add(formularioPanel, BorderLayout.CENTER);
        add(botonPanel, BorderLayout.SOUTH);
    }

    private void guardarProfesor() throws SQLException {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String email = emailField.getText();
        String tipoContrato = tipoField.getText();

        profesorController.guardarProfesor(new Profesor(0, nombre, apellido, email, tipoContrato));
        JOptionPane.showMessageDialog(this, "Profesor guardado exitosamente.");
    }
}
