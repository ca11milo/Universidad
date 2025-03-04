package org.example.view.ventanas;

import javax.swing.*;
import java.awt.*;

public class VentanaContenido extends JPanel {
    protected JButton botonCrear, botonLeer, botonActualizar, botonBorrar;

    public VentanaContenido(String titulo) {
        setLayout(new BorderLayout());


        JLabel tituloLabel = new JLabel(titulo, SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tituloLabel.setForeground(Color.WHITE);
        JPanel tituloPanel = new JPanel();
        tituloPanel.setBackground(new Color(50, 130, 200));
        tituloPanel.setLayout(new BorderLayout());
        tituloPanel.add(tituloLabel, BorderLayout.CENTER);
        add(tituloPanel, BorderLayout.NORTH);


        JPanel botonesPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        botonCrear = new JButton("Crear");
        botonLeer = new JButton("Leer");
        botonActualizar = new JButton("Actualizar");
        botonBorrar = new JButton("Borrar");

        estilizarBoton(botonCrear);
        estilizarBoton(botonLeer);
        estilizarBoton(botonActualizar);
        estilizarBoton(botonBorrar);

        botonesPanel.add(botonCrear);
        botonesPanel.add(botonLeer);
        botonesPanel.add(botonActualizar);
        botonesPanel.add(botonBorrar);

        add(botonesPanel, BorderLayout.CENTER);
    }

    private void estilizarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.PLAIN, 16));
        boton.setBackground(new Color(173, 216, 230));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createRaisedBevelBorder());
    }
}
