package org.example.view.profesor;

import org.example.controller.ProfesorController;
import org.example.model.Profesor;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ActualizarProfesor extends JPanel {
    private ProfesorController profesorController;
    private JTextField nombreField, apellidoField, emailField, tipoField, idField;

    public ActualizarProfesor(ProfesorController profesorController) {
        this.profesorController = profesorController;
        setLayout(new BorderLayout());

        // Panel de título
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 144, 255)); // Azul
        JLabel tituloLabel = new JLabel("PROFESOR");
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(tituloLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Panel de formulario
        JPanel formularioPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formularioPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // ID primero
        formularioPanel.add(new JLabel("ID del Profesor:"));
        idField = new JTextField();
        formularioPanel.add(idField);

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

        // Panel de botones
        JPanel botonPanel = new JPanel();
        JButton cargarButton = new JButton("Cargar");
        JButton guardarButton = new JButton("Guardar");
        JButton cancelarButton = new JButton("Cancelar");

        // Acciones de botones
        cargarButton.addActionListener(e -> cargarProfesor());
        guardarButton.addActionListener(e -> {
            try {
                guardarProfesor();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar el profesor.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        cancelarButton.addActionListener(e -> limpiarCampos());

        botonPanel.add(cargarButton);
        botonPanel.add(guardarButton);
        botonPanel.add(cancelarButton);

        add(formularioPanel, BorderLayout.CENTER);
        add(botonPanel, BorderLayout.SOUTH);
    }

    private void guardarProfesor() throws SQLException {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String nombre = nombreField.getText().trim();
            String apellido = apellidoField.getText().trim();
            String email = emailField.getText().trim();
            String tipo_contrato = tipoField.getText().trim();

            if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || tipo_contrato.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            profesorController.actualizarProfesor(new Profesor(id, nombre, apellido, email, tipo_contrato));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarProfesor() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            Profesor profesor = profesorController.obtenerProfesorPorId(id);
            if (profesor != null) {
                nombreField.setText(profesor.getNombres());
                apellidoField.setText(profesor.getApellidos());
                emailField.setText(profesor.getEmail());
                tipoField.setText(profesor.getTipoContrato());
            } else {
                JOptionPane.showMessageDialog(this, "Profesor no encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        idField.setText("");
        nombreField.setText("");
        apellidoField.setText("");
        emailField.setText("");
        tipoField.setText("");
    }
}
