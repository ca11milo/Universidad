package org.example.view.curso;

import org.example.controller.CursoController;
import org.example.model.Curso;
import org.example.model.Programa;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ActualizarCurso extends JPanel {
    private CursoController cursoController;
    private JTextField idField, nombreField;
    private JComboBox<Programa> programaComboBox;
    private JCheckBox activoCheckBox;

    public ActualizarCurso(CursoController cursoController) {
        this.cursoController = cursoController;

        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("Actualizar Curso", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);

        JPanel formularioPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formularioPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        formularioPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        formularioPanel.add(idField);

        formularioPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formularioPanel.add(nombreField);

        formularioPanel.add(new JLabel("Programa:"));
        programaComboBox = new JComboBox<>(); // Se debe llenar con los programas disponibles
        formularioPanel.add(programaComboBox);

        formularioPanel.add(new JLabel("Activo:"));
        activoCheckBox = new JCheckBox();
        formularioPanel.add(activoCheckBox);

        JButton cargarButton = new JButton("Cargar");
        JButton actualizarButton = new JButton("Actualizar");

        cargarButton.addActionListener(e -> cargarCurso());
        actualizarButton.addActionListener(e -> {
            try {
                actualizarCurso();
            } catch (SQLException ex) {
                System.out.println("Error al actualizar curso");
            }
        });

        JPanel botonPanel = new JPanel();
        botonPanel.add(cargarButton);
        botonPanel.add(actualizarButton);

        add(formularioPanel, BorderLayout.CENTER);
        add(botonPanel, BorderLayout.SOUTH);
    }

    private void cargarCurso() {
        try {
            int id = Integer.parseInt(idField.getText());
            Curso curso = cursoController.obtenerCursoPorId(id);
            if (curso != null) {
                nombreField.setText(curso.getNombre());
                programaComboBox.setSelectedItem(curso.getPrograma());
                activoCheckBox.setSelected(curso.isActivo());
            } else {
                JOptionPane.showMessageDialog(this, "Curso no encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarCurso() throws SQLException {
        int id = Integer.parseInt(idField.getText());
        String nombre = nombreField.getText();
        Programa programa = (Programa) programaComboBox.getSelectedItem();
        boolean activo = activoCheckBox.isSelected();

        cursoController.actualizarCurso(new Curso(id, nombre, programa, activo));
        JOptionPane.showMessageDialog(this, "Curso actualizado exitosamente.");
    }
}
