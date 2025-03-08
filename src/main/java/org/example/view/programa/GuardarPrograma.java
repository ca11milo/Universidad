package org.example.view.programa;


import org.example.controller.ProgramaController;
import org.example.model.Facultad;
import org.example.model.Persona;
import org.example.model.Programa;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;

public class GuardarPrograma extends JPanel {
    private ProgramaController programaController;
    private JTextField nombreField, duracionField, registroField, facultadIdField;

    public GuardarPrograma(ProgramaController programaController) {
        this.programaController = programaController;

        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("Crear Programa", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);

        JPanel formularioPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formularioPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        formularioPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formularioPanel.add(nombreField);

        formularioPanel.add(new JLabel("Duración:"));
        duracionField = new JTextField();
        formularioPanel.add(duracionField);

        formularioPanel.add(new JLabel("Registro:"));
        registroField = new JTextField();
        formularioPanel.add(registroField);

        formularioPanel.add(new JLabel("Id de la Facultad:"));
        facultadIdField = new JTextField();
        formularioPanel.add(facultadIdField);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> {
            try {
                guardarPrograma();
            } catch (SQLException ex) {
                System.out.println("Error al guardar el Programa");
            }
        });

        JPanel botonPanel = new JPanel();
        botonPanel.add(guardarButton);

        add(formularioPanel, BorderLayout.CENTER);
        add(botonPanel, BorderLayout.SOUTH);
    }

    private void guardarPrograma() throws SQLException {
        String nombre = nombreField.getText();
        Double duracion = Double.valueOf(duracionField.getText());
        Date registro = Date.valueOf(registroField.getText());
        Programa facultad = (Facultad) programaComboBox.getSelectedItem();

        programaController.guardarPrograma(new Programa(0, nombre, registro, duracion, facultad));
        JOptionPane.showMessageDialog(this, "Persona guardada exitosamente.");
    }
}
