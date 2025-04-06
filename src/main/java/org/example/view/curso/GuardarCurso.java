package org.example.view.curso;

import org.example.controller.CursoController;
import org.example.controller.ProgramaController;
import org.example.model.Curso;
import org.example.model.Programa;
import org.example.view.ventanasCRUD.VentanaGuardar;

import javax.swing.*;
import java.awt.*;

public class GuardarCurso extends VentanaGuardar<Curso> {
    private CursoController cursoController;
    private ProgramaController programaController;
    private JCheckBox activoCheckBox;

    public GuardarCurso(CursoController cursoController, ProgramaController programaController) {
        super("Registrar Curso", new String[]{"Nombre", "ID Programa", "Activo"});
        this.cursoController = cursoController;
        this.programaController = programaController;

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
    protected void guardarEntidad() {
        try {
            String nombre = campos[0].getText().trim();
            String idProgramaTexto = campos[1].getText().trim();
            boolean activo = activoCheckBox.isSelected();

            if (nombre.isBlank() || idProgramaTexto.isBlank()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idPrograma = Integer.parseInt(idProgramaTexto);

            Programa programaExistente = programaController.obtenerProgramaPorId(idPrograma);
            if (programaExistente == null) {
                JOptionPane.showMessageDialog(this, "No existe un programa con ID: " + idPrograma, "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Curso curso = new Curso(nombre, programaExistente, activo);
            cursoController.guardarCurso(curso);
            JOptionPane.showMessageDialog(this, "Curso guardado exitosamente.");
            limpiarCampos();
            activoCheckBox.setSelected(false);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID del programa debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el curso: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
