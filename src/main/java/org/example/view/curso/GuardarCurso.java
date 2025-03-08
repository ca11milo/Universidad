package org.example.view.curso;

import org.example.controller.CursoController;
import org.example.model.Curso;
import org.example.model.Programa;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GuardarCurso extends JPanel {
    private CursoController cursoController;
    private JTextField nombreField;
    private JComboBox<Programa> programaComboBox;
    private JCheckBox activoCheckBox;

    public GuardarCurso(CursoController cursoController) {
        this.cursoController = cursoController;

        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("Crear Curso", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);

        JPanel formularioPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formularioPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        formularioPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formularioPanel.add(nombreField);

        formularioPanel.add(new JLabel("Programa:"));
        programaComboBox = new JComboBox<>();
        formularioPanel.add(programaComboBox);

        formularioPanel.add(new JLabel("Activo:"));
        activoCheckBox = new JCheckBox();
        formularioPanel.add(activoCheckBox);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> {
            try {
                guardarCurso();
            } catch (SQLException ex) {
                System.out.println("Error al guardar curso");
            }
        });

        JPanel botonPanel = new JPanel();
        botonPanel.add(guardarButton);

        add(formularioPanel, BorderLayout.CENTER);
        add(botonPanel, BorderLayout.SOUTH);
    }

    private void guardarCurso() throws SQLException {
        String nombre = nombreField.getText();
        Programa programa = (Programa) programaComboBox.getSelectedItem();
        boolean activo = activoCheckBox.isSelected();

        cursoController.guardarCurso(new Curso(0, nombre, programa, activo));
        JOptionPane.showMessageDialog(this, "Curso guardado exitosamente.");
    }
}
