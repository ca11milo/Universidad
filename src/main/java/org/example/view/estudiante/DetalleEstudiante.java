package org.example.view.estudiante;

import org.example.controller.EstudianteController;
import org.example.model.Estudiante;

import javax.swing.*;
import java.awt.*;

public class DetalleEstudiante extends JPanel {
    private JTextField codigoField;
    private JTextField nombreField;
    private EstudianteController estudianteController;
    private JTabbedPane tabbedPane;

    public DetalleEstudiante(EstudianteController estudianteController) {
        this.estudianteController = estudianteController;

        setLayout(new BorderLayout());

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buscarPanel.add(new JLabel("Código:"));
        codigoField = new JTextField(10);
        buscarPanel.add(codigoField);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(e -> buscarEstudiante());
        buscarPanel.add(buscarButton);

        buscarPanel.add(new JLabel("Estudiante:"));
        nombreField = new JTextField(20);
        nombreField.setEditable(false);
        buscarPanel.add(nombreField);

        add(buscarPanel, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Historial Cursos", crearPanelHistorial());
        tabbedPane.addTab("Inscribir Curso", crearPanelInscribir());
        tabbedPane.addTab("Cursos", crearPanelCursos());
        tabbedPane.addTab("Docentes", crearPanelDocentes());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private void buscarEstudiante() {
        try {
            Double codigo = Double.parseDouble(codigoField.getText().trim());
            Estudiante estudiante = estudianteController.obtenerEstudiantePorCodigo(codigo);
            if (estudiante != null) {
                nombreField.setText(estudiante.getNombreCompleto());
            } else {
                JOptionPane.showMessageDialog(this, "Estudiante no encontrado.");
                nombreField.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El Codigo ingresado no es válido.");
        }
    }

    private JPanel crearPanelHistorial() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Aquí va el historial de cursos del estudiante."));
        return panel;
    }

    private JPanel crearPanelInscribir() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Aquí va el formulario para inscribir cursos."));
        return panel;
    }

    private JPanel crearPanelCursos() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Aquí se listan los cursos disponibles."));
        return panel;
    }

    private JPanel crearPanelDocentes() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Aquí se listan los docentes relacionados."));
        return panel;
    }
}
