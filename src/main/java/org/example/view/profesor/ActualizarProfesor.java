package org.example.view.profesor;

import org.example.controller.ProfesorController;
import org.example.model.Profesor;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ActualizarProfesor extends JPanel {
    private ProfesorController profesorController;
    private JTextField nombreField, apellidoField, emailField, idField, tipoContratoField;

    public ActualizarProfesor(ProfesorController profesorController) {
        this.profesorController = profesorController;

        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("PROFESOR", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tituloLabel.setOpaque(true);
        tituloLabel.setBackground(Color.BLUE);
        tituloLabel.setForeground(Color.WHITE);
        add(tituloLabel, BorderLayout.NORTH);

        JPanel formularioPanel = new JPanel(new GridLayout(5, 2, 10, 10));
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
        tipoContratoField = new JTextField();
        formularioPanel.add(tipoContratoField);

        formularioPanel.add(new JLabel("Ingresa el ID del profesor que deseas actualizar"));
        idField = new JTextField();
        formularioPanel.add(idField);

        JPanel botonPanel = new JPanel();
        JButton guardarButton = new JButton("Guardar");
        JButton cancelarButton = new JButton("Cancelar");
        JButton cargarButton = new JButton("Cargar");

        guardarButton.addActionListener(e -> {
            try {
                actualizarProfesor();
            } catch (SQLException ex) {
                System.out.println("Error al actualizar profesor");
            }
        });

        cargarButton.addActionListener(e -> cargarProfesor());

        botonPanel.add(cargarButton);
        botonPanel.add(guardarButton);
        botonPanel.add(cancelarButton);

        add(formularioPanel, BorderLayout.CENTER);
        add(botonPanel, BorderLayout.SOUTH);
    }

    private void actualizarProfesor() throws SQLException {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String email = emailField.getText();
        String tipoContrato = tipoContratoField.getText();
        int id = Integer.parseInt(idField.getText());

        profesorController.actualizarProfesor(new Profesor(id, nombre, apellido, email, tipoContrato));
        JOptionPane.showMessageDialog(this, "Profesor actualizado exitosamente.");
    }

    private void cargarProfesor() {
        try {
            int id = Integer.parseInt(idField.getText());
            Profesor profesor = profesorController.obtenerProfesorPorId(id);
            if (profesor != null) {
                nombreField.setText(profesor.getNombres());
                apellidoField.setText(profesor.getApellidos());
                emailField.setText(profesor.getEmail());
                tipoContratoField.setText(profesor.getTipoContrato());
            } else {
                JOptionPane.showMessageDialog(this, "Profesor no encontrado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.");
        }
    }
}
