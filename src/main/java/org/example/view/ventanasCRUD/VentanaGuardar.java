package org.example.view.ventanasCRUD;
import javax.swing.*;
import java.awt.*;

public abstract class VentanaGuardar<T> extends JPanel {
    protected JTextField[] campos;
    protected JPanel formularioPanel;

    public VentanaGuardar(String titulo, String[] etiquetas) {
        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel(titulo, SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);

        formularioPanel = new JPanel(new GridBagLayout()); // Usamos GridBagLayout
        formularioPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        campos = new JTextField[etiquetas.length];
        for (int i = 0; i < etiquetas.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formularioPanel.add(new JLabel(etiquetas[i] + ":"), gbc);

            gbc.gridx = 1;
            campos[i] = new JTextField(15);
            formularioPanel.add(campos[i], gbc);
        }

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> {
            try {
                guardarEntidad();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = etiquetas.length;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formularioPanel.add(guardarButton, gbc);

        add(formularioPanel, BorderLayout.CENTER);
    }

    protected abstract void guardarEntidad() throws Exception;
}
