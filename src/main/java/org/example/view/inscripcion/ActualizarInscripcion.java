package org.example.view.inscripcion;

import org.example.controller.EstudianteController;
import org.example.controller.InscripcionController;
import org.example.model.Curso;
import org.example.model.Estudiante;
import org.example.model.Inscripcion;
import org.example.view.ventanasCRUD.VentanaActualizar;

import javax.swing.*;

public class ActualizarInscripcion extends VentanaActualizar<Inscripcion> {
    private InscripcionController inscripcionController;
    private EstudianteController estudianteController;

    public ActualizarInscripcion(InscripcionController inscripcionController, EstudianteController estudianteController) {
        super("Actualizar Inscripción", new String[]{"ID Curso", "Año", "Semestre", "ID Estudiante"});
        this.inscripcionController = inscripcionController;
        this.estudianteController=estudianteController;
    }

    @Override
    protected void cargarEntidad() {
        try {
            int id = Integer.parseInt(idField.getText());
            Inscripcion inscripcion = inscripcionController.obtenerInscripcionPorId(id);
            if (inscripcion != null) {
                campos[0].setText(String.valueOf(inscripcion.getCurso().getID()));
                campos[1].setText(String.valueOf(inscripcion.getAño()));
                campos[2].setText(String.valueOf(inscripcion.getSemestre()));
                campos[3].setText(String.valueOf(inscripcion.getEstudiante().getID()));
            } else {
                JOptionPane.showMessageDialog(this, "Inscripción no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void guardarEntidad() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            int idCurso = Integer.parseInt(campos[0].getText().trim());
            int anio = Integer.parseInt(campos[1].getText().trim());
            int semestre = Integer.parseInt(campos[2].getText().trim());
            int idEstudiante = Integer.parseInt(campos[3].getText().trim());

            if (semestre < 1 || semestre > 2) {
                JOptionPane.showMessageDialog(this, "El semestre debe ser 1 o 2.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (anio < 1974 || anio > 2100) {
                JOptionPane.showMessageDialog(this, "El año ingresado no es válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Estudiante estudianteExistente = estudianteController.obtenerEstudiantePorId(idEstudiante);
            if (estudianteExistente == null) {
                JOptionPane.showMessageDialog(this, "El ID del estudiante no corresponde a un estudiante registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Curso curso = new Curso(idCurso);
            Inscripcion inscripcion = new Inscripcion(id, curso, anio, semestre, estudianteExistente);
            inscripcionController.actualizarInscripcion(inscripcion);

            JOptionPane.showMessageDialog(this, "Inscripción actualizada exitosamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Verifica que todos sean numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la inscripción: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
