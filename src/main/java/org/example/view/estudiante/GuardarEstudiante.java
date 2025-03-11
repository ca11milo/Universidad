package org.example.view.estudiante;

import org.example.controller.EstudianteController;
import org.example.model.Estudiante;
import org.example.model.Programa;
import org.example.view.ventanasCRUD.VentanaGuardar;

import javax.swing.*;
import java.awt.*;

public class GuardarEstudiante extends VentanaGuardar<Estudiante> {
    private EstudianteController estudianteController;
    private JCheckBox activoCheckBox;

    public GuardarEstudiante(EstudianteController estudianteController) {
        super("Registrar Estudiante", new String[]{"Nombre", "Apellido", "Email", "Codigo", "ID Programa", "Activo", "Promedio"});
        this.estudianteController = estudianteController;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        formularioPanel.remove(campos[5]);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formularioPanel.add(new JLabel("Activo:"), gbc);

        gbc.gridx = 1;
        activoCheckBox = new JCheckBox();
        formularioPanel.add(activoCheckBox, gbc);

        formularioPanel.revalidate();
        formularioPanel.repaint();
    }

    @Override
    protected void guardarEntidad() throws Exception {
        String nombre = campos[0].getText();
        String apellido = campos[1].getText();
        String email = campos[2].getText();
        double codigo = Double.parseDouble(campos[3].getText());
        int idPrograma = Integer.parseInt(campos[4].getText());
        boolean activo = activoCheckBox.isSelected();
        Double promedio = Double.parseDouble(campos[6].getText());

        Programa programa = new Programa(idPrograma);
        Estudiante estudiante = new Estudiante(nombre, apellido, email, codigo, programa, activo, promedio);

        estudianteController.guardarEstudiante(estudiante);
        JOptionPane.showMessageDialog(this, "Estudiante guardado exitosamente.");
    }
}
