package org.example.view.programa;

import org.example.controller.ProgramaController;
import org.example.model.Facultad;
import org.example.model.Programa;
import org.example.view.ventanasCRUD.VentanaGuardar;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GuardarPrograma extends VentanaGuardar<Programa> {
    private ProgramaController programaController;
    private JFormattedTextField fechaRegistroField;

    public GuardarPrograma(ProgramaController programaController) {
        super("Registrar Programa", new String[]{"Nombre", "Duración", "Fecha Registro", "ID Facultad"});
        this.programaController = programaController;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fechaRegistroField = new JFormattedTextField(dateFormat);
        fechaRegistroField.setColumns(15);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        formularioPanel.remove(campos[2]);
        formularioPanel.add(fechaRegistroField, gbc);

        formularioPanel.revalidate();
        formularioPanel.repaint();
    }

    @Override
    protected void guardarEntidad() throws Exception {
        String nombre = campos[0].getText();
        double duracion = Double.parseDouble(campos[1].getText());
        Date fechaRegistro = new SimpleDateFormat("yyyy-MM-dd").parse(fechaRegistroField.getText());
        int idFacultad = Integer.parseInt(campos[3].getText());

        Programa programa = new Programa(nombre,  fechaRegistro, duracion, new Facultad(idFacultad));

        programaController.guardarPrograma(programa);
        JOptionPane.showMessageDialog(this, "Programa guardado exitosamente.");
    }
}
