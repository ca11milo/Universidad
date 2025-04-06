package org.example.view.cursoProfesor;

import org.example.controller.CursoController;
import org.example.controller.CursoProfesorController;
import org.example.controller.ProfesorController;
import org.example.model.*;
import org.example.view.ventanasCRUD.VentanaActualizar;

import javax.swing.*;

public class ActualizarCursoProfesor extends VentanaActualizar<CursoProfesor> {
    private CursoProfesorController cursoProfesorController;
    private ProfesorController profesorController;
    private CursoController cursoController;

    public ActualizarCursoProfesor(CursoProfesorController cursoProfesorController, ProfesorController profesorController, CursoController cursoController) {
        super("Actualizar Curso Profesor", new String[]{"ID Curso", "Semestre", "Año", "ID Estudiante"});
        this.cursoProfesorController = cursoProfesorController;
        this.profesorController = profesorController;
        this.cursoController = cursoController;
    }

    @Override
    protected void cargarEntidad() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            CursoProfesor cursoProfesor = cursoProfesorController.obtenerCursoProfesorPorId(id);

            if (cursoProfesor != null) {
                campos[0].setText(String.valueOf(cursoProfesor.getProfesor().getID()));
                campos[1].setText(String.valueOf(cursoProfesor.getSemestre()));
                campos[2].setText(String.valueOf(cursoProfesor.getAño()));
                campos[3].setText(String.valueOf(cursoProfesor.getCurso().getID()));
            } else {
                JOptionPane.showMessageDialog(this, "Curso Profesor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void guardarEntidad() throws Exception {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String profesorTexto = campos[0].getText().trim();
            String anioTexto = campos[1].getText().trim();
            String semestreTexto = campos[2].getText().trim();
            String cursoTexto = campos[3].getText().trim();

            if (profesorTexto.isEmpty() || semestreTexto.isEmpty() || anioTexto.isEmpty() || cursoTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idProfesor = Integer.parseInt(profesorTexto);
            int semestre = Integer.parseInt(semestreTexto);
            int anio = Integer.parseInt(anioTexto);
            int idCurso = Integer.parseInt(cursoTexto);


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

            CursoProfesor cursoProfesor = new CursoProfesor(id, profesor, semestre, anio, curso);
            cursoProfesorController.actualizarCursoProfesor(cursoProfesor);
            JOptionPane.showMessageDialog(this, "Curso Profesor actualizado exitosamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Verifica los valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la inscripción: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
