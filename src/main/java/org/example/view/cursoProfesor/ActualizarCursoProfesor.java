package org.example.view.cursoProfesor;

import org.example.controller.CursoProfesorController;
import org.example.model.*;
import org.example.view.ventanasCRUD.VentanaActualizar;

import javax.swing.*;

public class ActualizarCursoProfesor extends VentanaActualizar<CursoProfesor> {
    private CursoProfesorController cursoProfesorController;

    public ActualizarCursoProfesor(CursoProfesorController cursoProfesorController) {
        super("Actualizar Curso Profesor", new String[]{"ID Curso", "Semestre", "Año", "ID Estudiante"});
        this.cursoProfesorController = cursoProfesorController;
    }

    @Override
    protected void cargarEntidad() {
        try {
            int id = Integer.parseInt(idField.getText());
            CursoProfesor cursoProfesor = cursoProfesorController.obtenerCursoProfesorPorId(id);
            if (cursoProfesor != null) {
                campos[0].setText(String.valueOf(cursoProfesor.getProfesor().getID()));
                campos[1].setText(String.valueOf(cursoProfesor.getSemestre()));
                campos[2].setText(String.valueOf(cursoProfesor.getAño()));
                campos[3].setText(String.valueOf(cursoProfesor.getCurso().getID()));
            } else {
                JOptionPane.showMessageDialog(this, "Cruso Profesor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void guardarEntidad() throws Exception {
        try {
            int id = Integer.parseInt(idField.getText());
            int idProfesor = Integer.parseInt(campos[0].getText());
            int semestre = Integer.parseInt(campos[2].getText());
            int año = Integer.parseInt(campos[1].getText());
            int idCurso = Integer.parseInt(campos[3].getText());

            Profesor profesor = new Profesor(idProfesor);
            Curso curso = new Curso(idCurso);

            CursoProfesor cursoProfesor = new CursoProfesor(id, profesor, semestre, año, curso);
            cursoProfesorController.actualizarCursoProfesor(cursoProfesor);

            JOptionPane.showMessageDialog(this, "Curso Profesor actualizado exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Verifica los valores.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
