package org.example.view;
import javax.swing.*;
import java.awt.*;

public class VentanaInterna extends JPanel {
    public VentanaInterna(String titulo) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Ventana: " + titulo, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        add(label, BorderLayout.CENTER);
    }
}
