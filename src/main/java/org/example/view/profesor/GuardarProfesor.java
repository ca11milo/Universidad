package org.example.view.profesor;

import org.example.controller.ProfesorController;
import org.example.model.Profesor;
import org.example.model.factory.PersonaFactory;
import org.example.view.ventanasCRUD.VentanaGuardar;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GuardarProfesor extends VentanaGuardar<Profesor> {
    private ProfesorController profesorController;
    private JComboBox<String> comboTipoContrato;

    public GuardarProfesor(ProfesorController profesorController) {

        super("Crear Profesor", new String[]{"Nombre", "Apellido", "Email"});
        this.profesorController = profesorController;

        JPanel panel = getFormularioPanel();

        comboTipoContrato = new JComboBox<>(new String[]{
                "Planta", "Ocasional", "Catedratico",
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("TipoContrato:"), gbc);
        gbc.gridx = 1;
        panel.add(comboTipoContrato, gbc);

        Component botonGuardar = null;
        for (Component comp : panel.getComponents()) {
            GridBagConstraints compGbc = ((GridBagLayout)panel.getLayout()).getConstraints(comp);
            if (comp instanceof JButton && compGbc.gridy == 3) {
                botonGuardar = comp;
                panel.remove(comp);
                break;
            }
        }

        if (botonGuardar != null) {
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);
            panel.add(botonGuardar, gbc);
        }

        panel.revalidate();
        panel.repaint();
    }

    @Override
    protected void guardarEntidad() throws SQLException {
        String nombre = campos[0].getText();
        String apellido = campos[1].getText();
        String email = campos[2].getText();
        String tipoContrato = (String) comboTipoContrato.getSelectedItem();

        if (nombre.isBlank() || apellido.isBlank() || email.isBlank() || tipoContrato == null || tipoContrato.isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Profesor profesor = PersonaFactory.crearProfesor(0, nombre, apellido, email, tipoContrato);
        profesorController.guardarProfesor(profesor);

        JOptionPane.showMessageDialog(this, "Profesor guardado exitosamente.");
        limpiarCampos();
    }

}