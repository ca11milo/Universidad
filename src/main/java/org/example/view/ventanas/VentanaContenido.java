package org.example.view.ventanas;

import javax.swing.*;
import java.awt.*;

public abstract class VentanaContenido extends JPanel {
    protected JTabbedPane tabbedPane;

    public VentanaContenido(String titulo, JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;

        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel(titulo, SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);
    }

    protected void agregarBoton(String texto, JPanel panel) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.PLAIN, 16));
        boton.setBackground(new Color(173, 216, 230));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createRaisedBevelBorder());
        boton.addActionListener(e -> abrirPestaña(texto));
        panel.add(boton);
    }

    protected abstract void abrirPestaña(String accion);

    protected JPanel crearTituloConCerrar(String titulo, JPanel panel) {
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
