package org.example.view.profesor;

import org.example.controller.ProfesorController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BorrarProfesor extends JPanel {
    private ProfesorController profesorController;
    private JTextField idField;

    public BorrarProfesor(ProfesorController profesorController) {
        this.profesorController = profesorController;
        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("Eliminar Profesor", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);

        JPanel formularioPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formularioPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        formularioPanel.add(new JLabel("ID del Profesor:"));
        idField = new JTextField();
        formularioPanel.add(idField);

        JPanel botonPanel = new JPanel();
        JButton borrarButton = new JButton("Eliminar");
        JButton cancelarButton = new JButton("Cancelar");

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarProfesor();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar profesor");
                }
            }
        });

        botonPanel.add(borrarButton);
        botonPanel.add(cancelarButton);

        add(formularioPanel, BorderLayout.CENTER);
        add(botonPanel, BorderLayout.SOUTH);
    }

    private void eliminarProfesor() throws SQLException {
        int id = Integer.parseInt(idField.getText());
        profesorController.eliminarProfesor(id);
        JOptionPane.showMessageDialog(this, "Profesor eliminado exitosamente.");
    }
}
