package org.example.view.ventanasCRUD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public abstract class VentanaBorrar extends JPanel {
    protected JTextField idField;
    protected JButton eliminarButton;
    protected JButton cancelarButton;

    public VentanaBorrar(String titulo) {
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 144, 255));
        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(tituloLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        mainPanel.setBackground(new Color(245, 245, 220));

        JLabel eliminarLabel = new JLabel("Eliminar " + titulo);
        eliminarLabel.setForeground(Color.BLUE);
        mainPanel.add(eliminarLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JLabel mensajeLabel = new JLabel("Ingresa el ID del " + titulo.toLowerCase() + " que deseas eliminar", SwingConstants.CENTER);
        mensajeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        centerPanel.add(mensajeLabel);

        idField = new JTextField();
        idField.setHorizontalAlignment(JTextField.CENTER);
        centerPanel.add(idField);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        eliminarButton = new JButton("Eliminar");
        cancelarButton = new JButton("Cancelar");

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminar();
                    JOptionPane.showMessageDialog(VentanaBorrar.this, titulo + " eliminado exitosamente.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(VentanaBorrar.this, "Error al eliminar " + titulo.toLowerCase() + ".", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelarButton.addActionListener(e -> idField.setText(""));

        buttonPanel.add(eliminarButton);
        buttonPanel.add(cancelarButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    protected abstract void eliminar() throws SQLException;
}