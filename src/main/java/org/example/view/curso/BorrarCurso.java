package org.example.view.curso;

import org.example.controller.CursoController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class BorrarCurso extends JPanel {
    private CursoController cursoController;
    private JTextField idField;

    public BorrarCurso(CursoController cursoController) {
        this.cursoController = cursoController;

        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 144, 255));
        JLabel tituloLabel = new JLabel("BORRAR CURSO");
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(tituloLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        mainPanel.setBackground(new Color(245, 245, 220));

        JLabel eliminarLabel = new JLabel("Eliminar Curso");
        eliminarLabel.setForeground(Color.BLUE);
        mainPanel.add(eliminarLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JLabel mensajeLabel = new JLabel("Ingresa el ID del curso que deseas eliminar", SwingConstants.CENTER);
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
                eliminarCurso();
                JOptionPane.showMessageDialog(this, "Curso eliminado exitosamente.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar curso.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> idField.setText(""));

        buttonPanel.add(eliminarButton);
        buttonPanel.add(cancelarButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void eliminarCurso() throws SQLException {
        try {
            int id = Integer.parseInt(idField.getText());
            String mensajeEliminado = cursoController.eliminarCurso(id);
            System.out.println(mensajeEliminado);
            idField.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
