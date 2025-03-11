package org.example.view.curso;

import org.example.controller.CursoController;
import org.example.model.Curso;
import org.example.model.Programa;
import org.example.view.ventanasCRUD.VentanaGuardar;

import javax.swing.*;
import java.awt.*;

public class GuardarCurso extends VentanaGuardar<Curso> {
    private CursoController cursoController;
    private JCheckBox activoCheckBox;

    public GuardarCurso(CursoController cursoController) {
        super("Registrar Curso", new String[]{"Nombre", "ID Programa", "Activo"});
        this.cursoController = cursoController;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        formularioPanel.remove(campos[2]);
        activoCheckBox = new JCheckBox();
        formularioPanel.add(activoCheckBox, gbc);

        formularioPanel.revalidate();
        formularioPanel.repaint();
    }

    @Override
    protected void guardarEntidad() throws Exception {
        String nombre = campos[0].getText();
        int idPrograma = Integer.parseInt(campos[1].getText());
        boolean activo = activoCheckBox.isSelected();

        Programa programa = new Programa(idPrograma);
        Curso curso = new Curso(nombre, programa, activo);

        cursoController.guardarCurso(curso);
        JOptionPane.showMessageDialog(this, "Curso guardado exitosamente.");
    }
}
