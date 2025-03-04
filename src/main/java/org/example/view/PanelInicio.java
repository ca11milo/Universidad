package org.example.view;

import javax.swing.*;
import java.awt.*;

public class PanelInicio extends JPanel {
    public PanelInicio(JTabbedPane tabbedPane) {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("UNIVERSIDAD", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        String[] nombresBotones = {"Persona", "Estudiante", "Profesor", "Facultad", "Programa", "Curso", "Inscripción", "Curso Profesor"};

        for (String nombre : nombresBotones) {
            JButton boton = new JButton(nombre);
            boton.addActionListener(new BotonListener(nombre, tabbedPane));
            botonesPanel.add(boton);
        }

        add(botonesPanel, BorderLayout.CENTER);
    }
}
