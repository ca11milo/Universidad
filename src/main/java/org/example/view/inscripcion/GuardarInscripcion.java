package org.example.view.inscripcion;

import org.example.controller.EstudianteController;
import org.example.controller.InscripcionController;
import org.example.model.Curso;
import org.example.model.Estudiante;
import org.example.model.Inscripcion;
import org.example.view.ventanasCRUD.VentanaGuardar;

import javax.swing.*;

public class GuardarInscripcion extends VentanaGuardar<Inscripcion> {
    private InscripcionController inscripcionController;
    private EstudianteController estudianteController;

    public GuardarInscripcion(InscripcionController inscripcionController, EstudianteController estudianteController) {
        super("Registrar Inscripción", new String[]{"ID Curso", "Año", "Semestre", "ID Estudiante"});
        this.inscripcionController = inscripcionController;
        this.estudianteController=estudianteController;
    }

    @Override
    protected void guardarEntidad() {
        try {
            String idCursoTexto = campos[0].getText().trim();
            String anioTexto = campos[1].getText().trim();
            String semestreTexto = campos[2].getText().trim();
            String idEstudianteTexto = campos[3].getText().trim();

            if (idCursoTexto.isEmpty() || anioTexto.isEmpty() || semestreTexto.isEmpty() || idEstudianteTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idCurso = Integer.parseInt(idCursoTexto);
            int anio = Integer.parseInt(anioTexto);
            int semestre = Integer.parseInt(semestreTexto);
            int idEstudiante = Integer.parseInt(idEstudianteTexto);

            if (anio < 1974 || anio > 2100) {
                JOptionPane.showMessageDialog(this, "El año ingresado no es válido (debe ser entre 1974 y 2100).", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (semestre < 1 || semestre > 2) {
                JOptionPane.showMessageDialog(this, "El semestre debe ser 1 o 2.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Estudiante estudiante = estudianteController.obtenerEstudiantePorId(idEstudiante);
            if (estudiante == null) {
                JOptionPane.showMessageDialog(this, "No existe un estudiante con el ID ingresado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Curso curso = new Curso(idCurso);
            Inscripcion inscripcion = new Inscripcion(0, curso, anio, semestre, estudiante);

            inscripcionController.guardarInscripcion(inscripcion);
            JOptionPane.showMessageDialog(this, "Inscripción guardada exitosamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Verifica que todos los valores numéricos sean válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la inscripción: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
