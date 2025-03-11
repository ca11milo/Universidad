package org.example.view.ventanasCRUD;

import javax.swing.*;
import java.awt.*;

public abstract class VentanaActualizar<T> extends JPanel {
    protected JTextField idField;
    protected JTextField[] campos;
    protected JPanel formularioPanel;

    public VentanaActualizar(String titulo, String[] etiquetas) {
        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel(titulo, SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);

        formularioPanel = new JPanel(new GridBagLayout());
        formularioPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campo de ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        formularioPanel.add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        idField = new JTextField(15);
        formularioPanel.add(idField, gbc);

        JButton cargarButton = new JButton("Cargar");
        cargarButton.addActionListener(e -> cargarEntidad());
        gbc.gridx = 2;
        formularioPanel.add(cargarButton, gbc);

        // Campos dinámicos
        campos = new JTextField[etiquetas.length];
        for (int i = 0; i < etiquetas.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            formularioPanel.add(new JLabel(etiquetas[i] + ":"), gbc);

            gbc.gridx = 1;
            campos[i] = new JTextField(15);
            formularioPanel.add(campos[i], gbc);
        }

        JButton actualizarButton = new JButton("Actualizar");
        actualizarButton.addActionListener(e -> {
            try {
                guardarEntidad();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = etiquetas.length + 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formularioPanel.add(actualizarButton, gbc);

        add(formularioPanel, BorderLayout.CENTER);
    }

    protected abstract void cargarEntidad();
    protected abstract void guardarEntidad() throws Exception;
}