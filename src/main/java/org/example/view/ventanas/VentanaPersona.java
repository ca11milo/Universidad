package org.example.view.ventanas;

import org.example.controller.PersonaController;
import org.example.view.persona.ActualizarPersona;
import org.example.view.persona.BorrarPersona;
import org.example.view.persona.GuardarPersona;
import org.example.view.persona.TablaPersonas;

import javax.swing.*;
import java.awt.*;

public class VentanaPersona extends JPanel {
    private PersonaController personaController;
    private JTabbedPane tabbedPane;

    public VentanaPersona(PersonaController personaController, JTabbedPane tabbedPane) {
        this.personaController = personaController;
        this.tabbedPane = tabbedPane;

        setLayout(new BorderLayout());


        JLabel tituloLabel = new JLabel("Gestión de Personas", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        agregarBoton("Crear", botonesPanel);
        agregarBoton("Leer", botonesPanel);
        agregarBoton("Actualizar", botonesPanel);
        agregarBoton("Borrar", botonesPanel);

        add(botonesPanel, BorderLayout.CENTER);
    }

    private void agregarBoton(String texto, JPanel panel) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.PLAIN, 16));
        boton.setBackground(new Color(173, 216, 230));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createRaisedBevelBorder());
        boton.addActionListener(e -> abrirPestaña(texto));
        panel.add(boton);
    }

    private void abrirPestaña(String accion) {
        JPanel nuevaPestaña;
        switch (accion) {
            case "Crear":
                nuevaPestaña = new GuardarPersona(personaController);
                break;
            case "Leer":
                nuevaPestaña = new TablaPersonas(personaController);
                break;
            case "Actualizar":
                nuevaPestaña = new ActualizarPersona(personaController);
                break;
            case "Borrar":
                nuevaPestaña= new BorrarPersona(personaController);
                break;
            default:
                return;
        }


        tabbedPane.addTab(accion, nuevaPestaña);
        int index = tabbedPane.indexOfComponent(nuevaPestaña);
        tabbedPane.setTabComponentAt(index, crearTituloConCerrar(accion, nuevaPestaña));
        tabbedPane.setSelectedComponent(nuevaPestaña);
    }

    private JPanel crearTituloConCerrar(String titulo, JPanel panel) {
        JPanel tabPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        tabPanel.setOpaque(false);

        JLabel tituloLabel = new JLabel(titulo);
        JButton cerrarBoton = new JButton("X");
        cerrarBoton.setMargin(new Insets(2, 5, 2, 5));
        cerrarBoton.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        cerrarBoton.setForeground(Color.RED);
        cerrarBoton.setFont(new Font("Arial", Font.BOLD, 12));
        cerrarBoton.setFocusPainted(false);
        cerrarBoton.setContentAreaFilled(false);

        cerrarBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cerrarBoton.setForeground(Color.WHITE);
                cerrarBoton.setBackground(Color.RED);
                cerrarBoton.setOpaque(true);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                cerrarBoton.setForeground(Color.RED);
                cerrarBoton.setOpaque(false);
            }
        });

        cerrarBoton.addActionListener(e -> {
            int index = tabbedPane.indexOfComponent(panel);
            if (index != -1) {
                tabbedPane.remove(index);
            }
        });

        tabPanel.add(tituloLabel);
        tabPanel.add(cerrarBoton);
        return tabPanel;
    }
}
