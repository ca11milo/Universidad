package org.example.view.estudiante;

import org.example.model.Programa;
import org.example.view.ventanasCRUD.VentanaActualizar;
import org.example.controller.EstudianteController;
import org.example.model.Estudiante;

import javax.swing.*;
import java.awt.*;

public class ActualizarEstudiante extends VentanaActualizar<Estudiante> {
    private EstudianteController estudianteController;
    private JCheckBox activoCheckBox;

    public ActualizarEstudiante(EstudianteController estudianteController) {
        super("Actualizar Estudiante", new String[]{"Nombre", "Apellido", "Email", "Codigo", "ID Programa", "Activo", "Promedio"});
        this.estudianteController = estudianteController;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        formularioPanel.remove(campos[5]);


        gbc.gridx = 1;
        gbc.gridy = 5;
        activoCheckBox = new JCheckBox();
        formularioPanel.add(activoCheckBox, gbc);

        formularioPanel.revalidate();
        formularioPanel.repaint();
    }

    @Override
    protected void guardarEntidad() throws Exception {
        int id = Integer.parseInt(idField.getText());
        String nombre = campos[0].getText();
        String apellido = campos[1].getText();
        String email = campos[2].getText();
        double codigo = Double.parseDouble(campos[3].getText());
        int idPrograma = Integer.parseInt(campos[4].getText());
        boolean activo = activoCheckBox.isSelected();
        double promedio = Double.parseDouble(campos[5].getText());

        Programa programa = new Programa(idPrograma);
        Estudiante estudiante = new Estudiante(id, nombre, apellido, email, codigo, programa, activo, promedio);

        estudianteController.actualizarEstudiante(estudiante);
        JOptionPane.showMessageDialog(this, "Estudiante actualizado exitosamente.");
    }

    @Override
    protected void cargarEntidad() {
        try {
            int id = Integer.parseInt(idField.getText());
            Estudiante estudiante = estudianteController.obtenerEstudiantePorId(id);
            if (estudiante != null) {
                campos[0].setText(estudiante.getNombres());
                campos[1].setText(estudiante.getApellidos());
                campos[2].setText(estudiante.getEmail());
                campos[3].setText(String.valueOf(estudiante.getCodigo()));
                campos[4].setText(String.valueOf(estudiante.getPrograma().getID()));
                activoCheckBox.setSelected(estudiante.getActivo());
                campos[5].setText(String.valueOf(estudiante.getPromedio()));
            } else {
                JOptionPane.showMessageDialog(this, "Estudiante no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
