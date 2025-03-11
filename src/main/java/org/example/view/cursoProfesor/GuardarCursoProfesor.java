package org.example.view.cursoProfesor;

import org.example.controller.CursoProfesorController;
import org.example.model.Curso;
import org.example.model.CursoProfesor;
import org.example.model.Profesor;
import org.example.view.ventanasCRUD.VentanaGuardar;

import javax.swing.*;

public class GuardarCursoProfesor extends VentanaGuardar<CursoProfesor> {
    private CursoProfesorController cursoProfesorController;

    public GuardarCursoProfesor(CursoProfesorController cursoProfesorController) {
        super("Registrar Curso Profesor", new String[]{"ID Profesor","Año", "Semestre", "ID Curso"});
        this.cursoProfesorController = cursoProfesorController;
    }

    @Override
    protected void guardarEntidad() throws Exception {
        try {
            int idProfesor = Integer.parseInt(campos[0].getText());
            int semestre = Integer.parseInt(campos[1].getText());
            int año = Integer.parseInt(campos[2].getText());
            int idCurso = Integer.parseInt(campos[3].getText());

            Profesor profesor = new Profesor(idProfesor);
            Curso curso = new Curso(idCurso);


            CursoProfesor cursoProfesor = new CursoProfesor(0, profesor, año, semestre, curso);
            cursoProfesorController.guardarCursoProfesor(cursoProfesor);

            JOptionPane.showMessageDialog(this, "Inscripción guardada exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Verifica los valores.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}