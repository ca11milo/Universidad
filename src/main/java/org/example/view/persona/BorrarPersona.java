package org.example.view.persona;

import org.example.controller.PersonaController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class BorrarPersona extends JPanel {
    private PersonaController personaController;
    private JTextField idField;

    public BorrarPersona(PersonaController personaController) {
        this.personaController = personaController;

        setLayout(new BorderLayout());


        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 144, 255));
        JLabel tituloLabel = new JLabel("PERSONA");
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(tituloLabel);
        add(headerPanel, BorderLayout.NORTH);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        mainPanel.setBackground(new Color(245, 245, 220)); // Fondo claro


        JLabel eliminarLabel = new JLabel("Eliminar Persona");
        eliminarLabel.setForeground(Color.BLUE);
        mainPanel.add(eliminarLabel, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JLabel mensajeLabel = new JLabel("Ingresa el ID de la persona que deseas eliminar", SwingConstants.CENTER);
        mensajeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        centerPanel.add(mensajeLabel);

        idField = new JTextField();
        idField.setHorizontalAlignment(JTextField.CENTER);
        centerPanel.add(idField);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton eliminarButton = new JButton("Eliminar");
        JButton cancelarButton = new JButton("Cancelar");

        eliminarButton.addActionListener(e -> {
            try {
                eliminarPersona();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar persona.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> idField.setText(""));

        buttonPanel.add(eliminarButton);
        buttonPanel.add(cancelarButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void eliminarPersona() throws SQLException {
        try {
            int id = Integer.parseInt(idField.getText());
            String mensajeEliminada = personaController.eliminarPersona(id);
            System.out.println(mensajeEliminada);
            idField.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
