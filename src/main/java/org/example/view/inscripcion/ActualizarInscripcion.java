package org.example.view.inscripcion;

import org.example.controller.InscripcionController;
import org.example.model.Curso;
import org.example.model.Estudiante;
import org.example.model.Inscripcion;
import org.example.view.ventanasCRUD.VentanaActualizar;

import javax.swing.*;

public class ActualizarInscripcion extends VentanaActualizar<Inscripcion> {
    private InscripcionController inscripcionController;

    public ActualizarInscripcion(InscripcionController inscripcionController) {
        super("Actualizar Inscripción", new String[]{"ID Curso", "Año", "Semestre", "ID Estudiante"});
        this.inscripcionController = inscripcionController;
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
    protected void guardarEntidad() throws Exception {
        try {
            int id = Integer.parseInt(idField.getText());
            int idCurso = Integer.parseInt(campos[0].getText());
            int anio = Integer.parseInt(campos[1].getText());
            int semestre = Integer.parseInt(campos[2].getText());
            int idEstudiante = Integer.parseInt(campos[3].getText());

            Curso curso = new Curso(idCurso);
            Estudiante estudiante = new Estudiante(idEstudiante);

            Inscripcion inscripcion = new Inscripcion(id, curso, anio, semestre, estudiante);
            inscripcionController.actualizarInscripcion(inscripcion);

            JOptionPane.showMessageDialog(this, "Inscripción actualizada exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Verifica los valores.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
