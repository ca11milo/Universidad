package org.example.view.profesor;

import org.example.controller.ProfesorController;
import org.example.model.Profesor;
import org.example.model.factory.PersonaFactory;
import org.example.view.ventanasCRUD.VentanaActualizar;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ActualizarProfesor extends VentanaActualizar<Profesor> {
    private ProfesorController profesorController;
    private JComboBox<String> comboTipoContrato;

    public ActualizarProfesor(ProfesorController profesorController) {

        super("Actualizar Profesor", new String[]{"Nombre", "Apellidos", "Email"});
        this.profesorController = profesorController;

        JPanel panel = formularioPanel;

        comboTipoContrato = new JComboBox<>(new String[]{
                "Planta", "Ocasional", "Catedratico",
        });

        Component botonActualizar = null;
        int posicionBoton = campos.length + 1;

        for (Component comp : panel.getComponents()) {
            GridBagConstraints gbc = ((GridBagLayout)panel.getLayout()).getConstraints(comp);
            if (gbc.gridy == posicionBoton) {
                botonActualizar = comp;
                panel.remove(comp);
                break;
            }
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = campos.length + 1; // Después del último campo
        panel.add(new JLabel("Tipo de Contrato:"), gbc);

        gbc.gridx = 1;
        panel.add(comboTipoContrato, gbc);

        if (botonActualizar != null) {
            gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 0;
            gbc.gridy = campos.length + 2;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(botonActualizar, gbc);
        }

        panel.revalidate();
        panel.repaint();
    }

    @Override
    protected void guardarEntidad() throws SQLException {
        String idTexto = idField.getText().trim();

        int id;
        try {
            id = Integer.parseInt(idTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = campos[0].getText();
        String apellido = campos[1].getText();
        String email = campos[2].getText();
        String tipoContrato = (String) comboTipoContrato.getSelectedItem();

        if (nombre.isBlank() || apellido.isBlank() || email.isBlank() || tipoContrato == null || tipoContrato.isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Profesor profesor = PersonaFactory.crearProfesor(id, nombre, apellido, email, tipoContrato);
        profesorController.actualizarProfesor(profesor);
        JOptionPane.showMessageDialog(this, "Profesor actualizado exitosamente.");
        limpiarCampos();
    }

    @Override
    protected void cargarEntidad() {
        String idTexto = idField.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo ID no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            Profesor profesor = profesorController.obtenerProfesorPorId(id);
            if (profesor != null) {
                campos[0].setText(profesor.getNombres());
                campos[1].setText(profesor.getApellidos());
                campos[2].setText(profesor.getEmail());

                comboTipoContrato.setSelectedItem(profesor.getTipoContrato()); // Setear valor en combo
            } else {
                JOptionPane.showMessageDialog(this, "Profesor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}