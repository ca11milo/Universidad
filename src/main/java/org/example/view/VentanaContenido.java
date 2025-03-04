package org.example.view;

import javax.swing.*;
import java.awt.*;

public class VentanaContenido extends JPanel {
    public VentanaContenido(String titulo) {
        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel(titulo, SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel tituloPanel = new JPanel(new BorderLayout());
        tituloPanel.add(tituloLabel, BorderLayout.CENTER);

        add(tituloPanel, BorderLayout.NORTH);
    }
}
