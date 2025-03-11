package org.example.view.curso;

import org.example.controller.CursoController;
import org.example.model.Curso;
import org.example.model.Programa;
import org.example.view.ventanasCRUD.VentanaActualizar;

import javax.swing.*;
import java.awt.*;

public class ActualizarCurso extends VentanaActualizar<Curso> {
    private CursoController cursoController;
    private JCheckBox activoCheckBox;

    public ActualizarCurso(CursoController cursoController) {
        super("Actualizar Curso", new String[]{"Nombre", "ID Programa", "Activo"});
        this.cursoController = cursoController;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
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
        int id = Integer.parseInt(idField.getText());
        String nombre = campos[0].getText();
        int idPrograma = Integer.parseInt(campos[1].getText());
        boolean activo = activoCheckBox.isSelected();

        Programa programa = new Programa(idPrograma);
        Curso curso = new Curso(id, nombre, programa, activo);

        cursoController.actualizarCurso(curso);
        JOptionPane.showMessageDialog(this, "Curso actualizado exitosamente.");
    }

    @Override
    protected void cargarEntidad() {
        try {
            int id = Integer.parseInt(idField.getText());
            Curso curso = cursoController.obtenerCursoPorId(id);
            if (curso != null) {
                campos[0].setText(curso.getNombre());
                campos[1].setText(String.valueOf(curso.getPrograma().getID()));
                activoCheckBox.setSelected(curso.isActivo());
            } else {
                JOptionPane.showMessageDialog(this, "Curso no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
