package org.example.view;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JTabbedPane tabbedPane;

    public VentanaPrincipal() {
        setTitle("Universidad - Gestión de Elementos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        JPanel inicioPanel = crearPanelInicio();
        tabbedPane.addTab("Inicio", inicioPanel);

        add(tabbedPane);
    }

    private JPanel crearPanelInicio() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("UNIVERSIDAD", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        String[] nombresBotones = {"Persona", "Estudiante", "Profesor", "Facultad", "Programa", "Curso", "Inscripción", "Curso Profesor"};

        for (String nombre : nombresBotones) {
            JButton boton = new JButton(nombre);
            boton.addActionListener(new BotonListener(nombre, tabbedPane));
            botonesPanel.add(boton);
        }

        panel.add(botonesPanel, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
