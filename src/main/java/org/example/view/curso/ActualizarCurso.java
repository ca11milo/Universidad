package org.example.view.curso;

import org.example.controller.CursoController;
import org.example.controller.ProgramaController;
import org.example.model.Curso;
import org.example.model.Programa;
import org.example.view.ventanasCRUD.VentanaActualizar;

import javax.swing.*;
import java.awt.*;

public class ActualizarCurso extends VentanaActualizar<Curso> {
    private CursoController cursoController;
    private ProgramaController programaController;
    private JCheckBox activoCheckBox;

    public ActualizarCurso(CursoController cursoController, ProgramaController programaController) {
        super("Actualizar Curso", new String[]{"Nombre", "ID Programa", "Activo"});
        this.cursoController = cursoController;
        this.programaController = programaController;

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
    protected void guardarEntidad() {
        try {
            String idTexto = idField.getText().trim();
            String nombre = campos[0].getText().trim();
            String idProgramaTexto = campos[1].getText().trim();
            boolean activo = activoCheckBox.isSelected();

            if (idTexto.isBlank() || nombre.isBlank() || idProgramaTexto.isBlank()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idTexto);
            int idPrograma = Integer.parseInt(idProgramaTexto);

            if (id <= 0 || idPrograma <= 0) {
                JOptionPane.showMessageDialog(this, "El ID y el ID del programa deben ser mayores que cero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Programa programa = programaController.obtenerProgramaPorId(idPrograma);
            if (programa == null) {
                JOptionPane.showMessageDialog(this, "El ID del programa no corresponde a ningún programa existente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Curso curso = new Curso(id, nombre, programa, activo);
            cursoController.actualizarCurso(curso);

            JOptionPane.showMessageDialog(this, "Curso actualizado exitosamente.");
            limpiarCampos();
            activoCheckBox.setSelected(false);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID y el ID del programa deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el curso: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void cargarEntidad() {
        try {
            String idTexto = idField.getText().trim();
            if (idTexto.isBlank()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idTexto);
            Curso curso = cursoController.obtenerCursoPorId(id);

            if (curso != null) {
                campos[0].setText(curso.getNombre());
                campos[1].setText(String.valueOf(curso.getPrograma().getID()));
                activoCheckBox.setSelected(curso.isActivo());
            } else {
                JOptionPane.showMessageDialog(this, "Curso no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el curso: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
