package org.example.view.ventanasCRUD;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public abstract class VentanaGuardar<T> extends JPanel {
    protected JTextField[] campos;

    public VentanaGuardar(String titulo, String[] etiquetas) {
        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel(titulo, SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);

        JPanel formularioPanel = new JPanel(new GridLayout(etiquetas.length, 2, 10, 10));
        formularioPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        campos = new JTextField[etiquetas.length];
        for (int i = 0; i < etiquetas.length; i++) {
            formularioPanel.add(new JLabel(etiquetas[i] + ":"));
            campos[i] = new JTextField();
            formularioPanel.add(campos[i]);
        }

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> {
            try {
                guardarEntidad();
                JOptionPane.showMessageDialog(this, "Guardado exitosamente.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel botonPanel = new JPanel();
        botonPanel.add(guardarButton);

        add(formularioPanel, BorderLayout.CENTER);
        add(botonPanel, BorderLayout.SOUTH);
    }

    protected abstract void guardarEntidad() throws SQLException;
}

