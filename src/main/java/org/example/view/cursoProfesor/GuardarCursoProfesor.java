package org.example.view.cursoProfesor;

import org.example.controller.CursoController;
import org.example.controller.CursoProfesorController;
import org.example.controller.ProfesorController;
import org.example.model.Curso;
import org.example.model.CursoProfesor;
import org.example.model.Profesor;
import org.example.view.ventanasCRUD.VentanaGuardar;

import javax.swing.*;

public class GuardarCursoProfesor extends VentanaGuardar<CursoProfesor> {
    private CursoProfesorController cursoProfesorController;
    private ProfesorController profesorController;
    private CursoController cursoController;

    public GuardarCursoProfesor(CursoProfesorController cursoProfesorController, ProfesorController profesorController, CursoController cursoController) {
        super("Registrar Curso Profesor", new String[]{"ID Profesor","Año", "Semestre", "ID Curso"});
        this.cursoProfesorController = cursoProfesorController;
        this.profesorController = profesorController;
        this.cursoController = cursoController;
    }

    @Override
    protected void guardarEntidad() throws Exception {
        try {
            String profesorTexto = campos[0].getText().trim();
            String semestreTexto = campos[1].getText().trim();
            String añoTexto = campos[2].getText().trim();
            String cursoTexto = campos[3].getText().trim();

            if (profesorTexto.isEmpty() || semestreTexto.isEmpty() || añoTexto.isEmpty() || cursoTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idProfesor = Integer.parseInt(profesorTexto);
            int semestre = Integer.parseInt(semestreTexto);
            int año = Integer.parseInt(añoTexto);
            int idCurso = Integer.parseInt(cursoTexto);

            if (semestre < 1 || semestre > 2) {
                JOptionPane.showMessageDialog(this, "El semestre debe ser 1 o 2.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (año < 1974 || año > 2100) {
                JOptionPane.showMessageDialog(this, "El año ingresado no es válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Profesor profesor = profesorController.obtenerProfesorPorId(idProfesor);
            if (profesor == null) {
                JOptionPane.showMessageDialog(this, "El ID del profesor no corresponde a ningún profesor existente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Curso curso = cursoController.obtenerCursoPorId(idCurso);
            if (curso == null) {
                JOptionPane.showMessageDialog(this, "El ID del curso no corresponde a ningún curso existente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            CursoProfesor cursoProfesor = new CursoProfesor(0, profesor, año, semestre, curso);
            cursoProfesorController.guardarCursoProfesor(cursoProfesor);

            JOptionPane.showMessageDialog(this, "Inscripción guardada exitosamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Asegúrate de ingresar solo números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}