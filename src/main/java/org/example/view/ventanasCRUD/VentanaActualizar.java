package org.example.view.ventanasCRUD;


import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public abstract class VentanaActualizar<T> extends JPanel {
    protected JTextField idField;
    protected JPanel formularioPanel;
    protected JButton guardarButton, cancelarButton, cargarButton;

    public VentanaActualizar(String titulo) {
        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel(titulo.toUpperCase(), SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tituloLabel.setOpaque(true);
        tituloLabel.setBackground(Color.BLUE);
        tituloLabel.setForeground(Color.WHITE);
        add(tituloLabel, BorderLayout.NORTH);

        formularioPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // Grid adaptable
        formularioPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Campo ID
        formularioPanel.add(new JLabel("ID de la entidad:"));
        idField = new JTextField();
        formularioPanel.add(idField);

        agregarCampos(); // Método abstracto para que cada subclase agregue sus propios campos

        JPanel botonPanel = new JPanel();
        guardarButton = new JButton("Guardar");
        cancelarButton = new JButton("Cancelar");
        cargarButton = new JButton("Cargar");

        guardarButton.addActionListener(e -> {
            try {
                guardarEntidad();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar la entidad.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cargarButton.addActionListener(e -> cargarEntidad());

        botonPanel.add(cargarButton);
        botonPanel.add(guardarButton);
        botonPanel.add(cancelarButton);

        add(formularioPanel, BorderLayout.CENTER);
        add(botonPanel, BorderLayout.SOUTH);
    }

    // Método para que cada subclase agregue sus propios campos
    protected abstract void agregarCampos();

    // Método para guardar la entidad
    protected abstract void guardarEntidad() throws SQLException;

    // Método para cargar la entidad
    protected abstract void cargarEntidad();
}
