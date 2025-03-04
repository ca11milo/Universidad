package org.example.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JTabbedPane tabbedPane;

    public VentanaPrincipal() {
        setTitle("Universidad");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        add(tabbedPane);

        agregarPestañaPrincipal();
    }

    private void agregarPestañaPrincipal() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(3, 3, 10, 10));

        String[] botones = {"Persona", "Estudiante", "Profesor", "Facultad", "Programa", "Curso", "Inscripción", "Curso Profesor"};

        for (String nombre : botones) {
            JButton boton = new JButton(nombre);
            boton.setBackground(Color.CYAN);
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    agregarNuevaPestaña(nombre);
                }
            });
            panelPrincipal.add(boton);
        }

        tabbedPane.addTab("Inicio", panelPrincipal);
    }

    private void agregarNuevaPestaña(String titulo) {
        JPanel nuevaPestaña = new JPanel();
        nuevaPestaña.add(new JLabel("Ventana de " + titulo));
        tabbedPane.addTab(titulo, nuevaPestaña);
        tabbedPane.setSelectedComponent(nuevaPestaña);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
